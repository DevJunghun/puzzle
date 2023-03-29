package com.puzzle.email.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, String> {
    UserEmail findByEmailAndDeletedFalse(final String email);
    UserEmail findByEmail(final String email);
}
