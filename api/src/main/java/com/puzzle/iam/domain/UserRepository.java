package com.puzzle.iam.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndDeletedFalse(final String email);
    User findByEmail(final String email);

    User findByUuidAndDeletedFalse(final String uuid);
    User findByUuid(final String uuid);

    User findByUsernameAndDeletedFalse(final String username);
    User findByUsername(final String username);

}
