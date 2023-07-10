package com.puzzle.address.domain;

import com.puzzle.address.controller.dto.AddressGroupDto;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.iam.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressGroupTransactionService {
    private final AddressGroupService service;
    private final AddressCompositeService addressCompositeService;

    @Transactional
    public AddressGroupDto.GetAllGroups.Response findAll(final User user) {
        final var groups = service.findAll(user.getUuid(), BooleanDelete.FALSE);

        final var groupDtos = groups.stream()
                .map(this::getGroupDto)
                .toList();

        return new AddressGroupDto.GetAllGroups.Response(groupDtos);
    }

    @Transactional
    public AddressGroupDto.GetAllGroups.Response findAll2(final User user) {
        final var groups = service.findAll(user.getUuid(), BooleanDelete.FALSE);

        final var groupDtos = groups.stream()
                .map(this::getGroupDto)
                .toList();

        return new AddressGroupDto.GetAllGroups.Response(groupDtos);
    }

    private AddressGroupDto.Group getGroupDto(final AddressGroup group) {
        final var groupDto = new AddressGroupDto.Group(group.getName(), group.getUuid(), null, null, 0);

        assignAddress(groupDto, group);
        assignInnerGroup(groupDto, group);

        return groupDto;

    }

    private void assignAddress(final AddressGroupDto.Group groupDto, final AddressGroup group) {
        final var addresses = getAllAddressInGroup(group);
        groupDto.setAddress(addresses);
        groupDto.setSize(addresses.size());
    }

    private void assignInnerGroup(final AddressGroupDto.Group groupDto, final AddressGroup group) {
        final var groupInGroups = findAllByParentGroup(group.getUuid());
        final var groupDtos = groupInGroups.stream()
                .map(this::getGroupDto)
                .toList();

        groupDto.setInnerGroups(groupDtos.size() == 0 ? null : groupDtos);
        groupDto.addAllAddressSize(groupDtos);
    }

    private List<AddressGroupDto.Address> getAllAddressInGroup(final AddressGroup group) {
        return addressCompositeService.findAll(group.getUuid())
                .stream()
                .map(address -> new AddressGroupDto.Address(address.getName(), address.getUuid()))
                .toList();
    }

    private List<AddressGroup> findAllByParentGroup(final String parentUuid) {
        return service.findAllByParentGroup(parentUuid, BooleanDelete.FALSE);
    }
}
