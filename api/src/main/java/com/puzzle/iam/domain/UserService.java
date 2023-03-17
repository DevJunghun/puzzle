package com.puzzle.iam.domain;

import com.puzzle.api.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public String create(final Users user) {
        auditNewUser(user);
        repository.save(user);

        return user.getUuid();
    }

    public Users findByEmailNotNull(final String email) {
        final var user = repository.findByEmailAndDeletedFalse(email);
        validUserNotNull(user);

        return user;
    }

    public Users findByUuidNotNull(final String uuid) {
        final var user = repository.findByEmailAndDeletedFalse(uuid);
        validUserNotNull(user);

        return user;
    }

    public void save(final Users user) {
        repository.save(user);
    }

    private void validUserNotNull(final Users user) {
        if (user == null) {
            throw new ApiException("User Not Found");
        }
    }

    private void auditNewUser(final Users user) {
        final var now = LocalDateTime.now();

        user.setCreatedAt(now);
        user.setUpdatedAt(now);
    }
}
