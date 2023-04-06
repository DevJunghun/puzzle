package com.puzzle.address.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressGroupRepository  extends JpaRepository<AddressGroup, String> {
    List<AddressGroup> findAllByUserUuid(final String userUuid);
    List<AddressGroup> findAllByUserUuidAndDeletedIsFalse(final String userUuid);

    List<AddressGroup> findAllByParentGroupUuid(final String parentUuid);
    List<AddressGroup> findAllByParentGroupUuidAndDeletedIsFalse(final String parentUuid);

    AddressGroup findByUuidAndUserUuid(final String uuid, final String userUuid);
    AddressGroup findByUuidAndUserUuidAndDeletedIsFalse(final String uuid, final String userUuid);
}
