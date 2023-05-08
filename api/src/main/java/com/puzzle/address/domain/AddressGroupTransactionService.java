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

    private AddressGroupDto.Group getGroupDto(final AddressGroup group) {
        final var groupDto = new AddressGroupDto.Group(group.getName(), group.getUuid(), null, null);

        assignAddressInGroup(groupDto, group);
        assignInnerGroupInGroup(groupDto, group);

        return groupDto;

    }

    private void assignAddressInGroup(final AddressGroupDto.Group groupDto, final AddressGroup group) {
        final var addresses = getAllAddressInGroup(group);
        groupDto.setAddress(addresses);
    }

    private void assignInnerGroupInGroup(final AddressGroupDto.Group groupDto, final AddressGroup group) {
        final var groupInGroup = findAllByParentGroup(group.getUuid());
        final var nameOfGroupInGroup = groupInGroup == null ? null : groupInGroup.stream()
                .map(AddressGroupDto.Group::getName)
                .toList();

        groupDto.setInnerGroupNames(nameOfGroupInGroup);
    }

    private List<AddressGroupDto.Address> getAllAddressInGroup(final AddressGroup group) {
        return addressCompositeService.findAll(group.getUuid())
                .stream()
                .map(address -> new AddressGroupDto.Address(address.getName(), address.getUuid()))
                .toList();
    }

    private List<AddressGroupDto.Group> findAllByParentGroup(final String parentUuid) {
        final var groups = service.findAllByParentGroup(parentUuid, BooleanDelete.FALSE);

        if (groups.size() == 0) {
            return null;
        }

        return groups.stream()
                .map(this::getGroupDto)
                .toList();

    }
}
