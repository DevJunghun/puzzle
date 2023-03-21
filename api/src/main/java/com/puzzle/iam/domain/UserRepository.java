package com.puzzle.iam.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmailAndDeletedFalse(final String email);
    Users findByEmail(final String email);
    Users findByUuidAndDeletedFalse(final String uuid);
    Users findByUuid(final String uuid);

}
