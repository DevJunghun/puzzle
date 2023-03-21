package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.domain.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public String create(final User user) {
        auditNewUser(user);
        repository.save(user);

        return user.getUuid();
    }

    public User findByEmail(final String email, final BooleanDelete delete, final BooleanValidate validate) {
        User user;

        if (delete.isTrue()) {
            user = repository.findByEmailAndDeletedFalse(email);

        } else {
            user = repository.findByEmail(email);
        }

        validUserNotNull(user, validate, email);

        return user;
    }

    public User findByUuid(final String uuid, final BooleanDelete delete, final BooleanValidate validate) {
        User user;

        if (delete.isTrue()) {
            user = repository.findByUuidAndDeletedFalse(uuid);

        } else {
            user = repository.findByUuid(uuid);
        }

        validUserNotNull(user, validate, uuid);

        return user;
    }

    public void save(final User user) {
        repository.save(user);
    }

    private void validUserNotNull(final User user, BooleanValidate validate, String var) {
        if (validate.isTrue() && user == null) {
            throw new UserNotFoundException(var);
        }
    }

    private void auditNewUser(final User user) {
        final var now = LocalDateTime.now();

        user.setCreatedAt(now);
        user.setUpdatedAt(now);
    }
}
