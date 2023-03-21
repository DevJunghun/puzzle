package com.puzzle.iam.domain;

import com.puzzle.iam.type.AuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthLogCompositeService {
    private final AuthLogService service;

    public void create(final String userUuid, final AuthType type, final boolean isSuccess, final String ip, final String errorMessage) {
        final var authLog = new AuthLog();

        authLog.setUserUuid(userUuid);
        authLog.setType(type);
        authLog.setSuccess(isSuccess);
        authLog.setIp(ip);
        authLog.setErrorMessage(errorMessage);
        authLog.setCreatedAt(LocalDateTime.now());

        service.create(authLog);
    }
}
