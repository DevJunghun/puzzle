package com.puzzle.address.domain;

import com.puzzle.address.domain.exceptions.AddressNotFound;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public Address create(final Address address) {
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());

        return repository.save(address);
    }

    public List<Address> findAll(final String groupUuid, BooleanDelete delete) {
        if (delete.isTrue()) {
            return repository.findAllByGroupUuid(groupUuid);
        } else {
            return repository.findAllByGroupUuidAndDeletedIsFalse(groupUuid);
        }
    }

    public  Address find(final String uuid, final BooleanDelete delete, final BooleanValidate validate) {
        Address address;

        if (delete.isTrue()){
            address =  repository.findByUuid(uuid);
        } else {
            address =  repository.findByUuidAndDeletedIsFalse(uuid);
        }

        validate(address, validate, uuid);

        return address;
    }

    public void update(final Address address) {
        address.setUpdatedAt(LocalDateTime.now());

        repository.save(address);
    }

    public void delete(final Address address) {
        address.setDeleted(true);
        address.setUpdatedAt(LocalDateTime.now());

        repository.save(address);
    }

    private void validate(final Address address, final BooleanValidate validate, final String uuid) {
        if (validate.isTrue()) {
            if (address == null) {
                throw new AddressNotFound(uuid);
            }
        }
    }
}
