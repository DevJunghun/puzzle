package com.puzzle.iam.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLogService {
    private final AuthLogRepository repository;

    public void create(final AuthLog authLog) {
        repository.save(authLog);
    }
}
