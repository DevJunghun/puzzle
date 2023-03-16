package com.puzzle.iam.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public String create(Users user) {
        auditNewUser(user);
        repository.save(user);

        return user.getUuid();
    }

    private void auditNewUser(final Users user) {
        final var now = LocalDateTime.now();

        user.setCreatedAt(now);
        user.setUpdatedAt(now);
    }
}
