package com.puzzle.address.domain;

import com.puzzle.address.controller.dto.AddressGroupDto;
import com.puzzle.address.domain.exceptions.DefaultAddressGroupCanNotBeUpdateException;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressGroupCompositeService {
    private final AddressGroupService service;
    private final AddressGroupTrigger addressGroupTrigger;

    private final static String DEFAULT_GROUP_NAME = "default";

    public AddressGroup find(final String uuid) {
        return service.findByUuid(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
    }

    @Transactional
    public AddressGroupDto.CreateParentGroup.Response createDefaultGroup(final String userUuid) {
        final var group = createParentGroup(userUuid, DEFAULT_GROUP_NAME);
        group.setDefaultGroup(true);

        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    @Transactional
    public AddressGroupDto.Response createParentGroup(final User user, final AddressGroupDto.CreateParentGroup.Request request) {
        final var group = createParentGroup(user.getUuid(), request.getName());
        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    @Transactional
    public AddressGroupDto.Response createChildGroup(final User user, final String groupUuid, final AddressGroupDto.CreateParentGroup.Request request) {
        final var group = createChildGroup(user.getUuid(), groupUuid, request.getName());
        final var savedGroup = service.create(group);

        return new AddressGroupDto.CreateParentGroup.Response(savedGroup.getUuid());
    }

    @Transactional
    public void update(final User user, final String groupUuid, final AddressGroupDto.UpdateGroup.Request request) {
        final var group = service.findByUuid(user.getUuid(), groupUuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
        checkIfDefault(group);

        group.setName(request.getName());
        service.update(group);
    }

    @Transactional
    public void delete(final User user, final String groupUuid) {
        final var group = service.findByUuid(user.getUuid(), groupUuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
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

    private void checkIfDefault(final AddressGroup group) {
        if (group.isDefaultGroup()) {
            throw new DefaultAddressGroupCanNotBeUpdateException(group.getUuid());
        }
    }

}
