package com.puzzle.address.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findAllByGroupUuid(final String groupUuid);
    List<Address> findAllByGroupUuidAndDeletedIsFalse(final String groupUuid);

    Address findByUuid(final String uuid);
    Address findByUuidAndDeletedIsFalse(final String uuid);
}
