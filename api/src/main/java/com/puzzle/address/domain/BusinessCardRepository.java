package com.puzzle.address.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCardRepository extends JpaRepository<BusinessCard, String> {
    BusinessCard findByUuid(final String uuid);
    BusinessCard findByUuidAndDeletedIsFalse(final String uuid);

    BusinessCard findByAddressUuid(final String addressUuid);
    BusinessCard findByAddressUuidAndDeletedIsFalse(final String addressUuid);
}
