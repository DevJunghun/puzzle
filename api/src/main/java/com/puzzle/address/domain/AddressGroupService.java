package com.puzzle.address.domain;

import com.puzzle.address.domain.exceptions.AddressGroupNotFound;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressGroupService {
    private final AddressGroupRepository repository;

    public List<AddressGroup> findAll(final String userUuid, BooleanDelete delete) {
        if (delete.isTrue()) {
            return repository.findAllByUserUuid(userUuid);

        } else {
            return repository.findAllByUserUuidAndDeletedIsFalse(userUuid);
        }
    }

    public List<AddressGroup> findAllByParentGroup(final String parentUuid, BooleanDelete delete) {
        if (delete.isTrue()) {
            return repository.findAllByParentGroupUuid(parentUuid);

        } else {
            return repository.findAllByParentGroupUuidAndDeletedIsFalse(parentUuid);
        }
    }

    public AddressGroup findByUuid(final String userUuid, final String uuid, final BooleanDelete delete, final BooleanValidate validate) {
        AddressGroup addressGroup;

        if (delete.isTrue()) {
            addressGroup =  repository.findByUuidAndUserUuid(userUuid, uuid);

        } else {
            addressGroup =  repository.findByUuidAndUserUuidAndDeletedIsFalse(userUuid, uuid);
        }

        checkValidate(validate, addressGroup, uuid);

        return addressGroup;
    }

    public void update(final AddressGroup group) {
        group.setUpdatedAt(LocalDateTime.now());

        repository.save(group);
    }

    public void delete(final AddressGroup group) {
        group.setDeleted(true);
        group.setUpdatedAt(LocalDateTime.now());

        repository.save(group);
    }

    public AddressGroup create(final AddressGroup group) {
        final var now = LocalDateTime.now();
        group.setDeleted(false);
        group.setCreatedAt(now);
        group.setUpdatedAt(now);

        return repository.save(group);
    }

    private void checkValidate(final BooleanValidate validate, final AddressGroup group, final String uuid) {
        if (validate.isTrue() && group == null) {
            throw new AddressGroupNotFound(uuid);
        }
    }
}
