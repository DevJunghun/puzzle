package com.puzzle.address.domain;

import com.puzzle.address.controller.dto.AddressGroupDto;
import com.puzzle.address.domain.exceptions.DefaultAddressGroupCanNotBeUpdateException;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressGroupCompositeService {
    private final AddressGroupService service;
    private final AddressCompositeService addressCompositeService;
    private final AddressGroupTrigger addressGroupTrigger;

    private final static String DEFAULT_GROUP_NAME = "default";

    public AddressGroupDto.GetAllGroups.Response findAll(final String userUuid) {
        final var groups = service.findAll(userUuid, BooleanDelete.FALSE);


        final var groupDtos = groups.stream()
                .map(this::getGroupDto)
                .toList();

        return new AddressGroupDto.GetAllGroups.Response(groupDtos);
    }

    public AddressGroupDto.CreateParentGroup.Response createDefaultGroup(final String userUuid) {
        final var group = createParentGroup(userUuid,DEFAULT_GROUP_NAME);
        group.setDefaultGroup(true);

        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    public AddressGroupDto.CreateParentGroup.Response createParentGroup(final String userUuid, final AddressGroupDto.CreateParentGroup.Request request) {
        final var group = createParentGroup(userUuid, request.getName());
        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    public AddressGroupDto.CreateParentGroup.Response createChildGroup(final String userUuid, final String groupUuid, final AddressGroupDto.CreateParentGroup.Request request) {
        final var group = createChildGroup(userUuid, groupUuid, request.getName());
        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    public void update(final String userUuid, final String groupUuid, final AddressGroupDto.UpdateGroup.Request request) {
        final var group = service.findByUuid(userUuid, groupUuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
        checkIfDefault(group);

        group.setName(request.getName());
        service.update(group);
    }

    public void delete(final String userUuid,final String groupUuid) {
        final var group = service.findByUuid(userUuid, groupUuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
        checkIfDefault(group);

        addressGroupTrigger.deleteAllAddress(group);

        service.delete(group);
    }

    private AddressGroup createChildGroup(final String userUuid, final String groupUuid, final String name) {
        final var group = new AddressGroup();

        group.setName(name);
        group.setHasParentGroup(true);
        group.setParentGroupUuid(groupUuid);
        group.setUserUuid(userUuid);
        group.setDefaultGroup(false);

        return group;
    }

    private AddressGroup createParentGroup(final String userUuid, final String name) {
        final var group = new AddressGroup();

        group.setName(name);
        group.setHasParentGroup(false);
        group.setUserUuid(userUuid);
        group.setDefaultGroup(false);

        return group;
    }

    private AddressGroupDto.Group getGroupDto(final AddressGroup group) {
        final var groupDto = new AddressGroupDto.Group(group.getName(), group.getUuid(), null, null);

        final var addresses = getAllAddressInGroup(group);
        groupDto.setAddress(addresses);

        final var groupInGroup = findAllByParentGroup(group.getUuid());
        groupDto.setGroup(groupInGroup);

        return groupDto;
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

    private List<AddressGroupDto.Address> getAllAddressInGroup(final AddressGroup group) {
        return addressCompositeService.findAll(group.getUuid())
                .stream()
                .map(address -> new AddressGroupDto.Address(address.getName(), address.getUuid()))
                .toList();
    }

    private void checkIfDefault(final AddressGroup group) {
        if (group.isDefaultGroup()) {
            throw new DefaultAddressGroupCanNotBeUpdateException(group.getUuid());
        }
    }

}
