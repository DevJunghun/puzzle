package com.puzzle.iam.domain;

import com.puzzle.iam.type.AuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthLogCompositeService {
    private final AuthLogService service;

    public void create(final String userUuid, final AuthType type, final boolean success, final String ip, final String errorMessage) {
        final var authLog = new AuthLog();

        authLog.setUserUuid(userUuid);
        authLog.setType(type);
        authLog.setSuccess(success);
        authLog.setIp(ip);
        authLog.setErrorMessage(errorMessage);
        authLog.setCreatedAt(LocalDateTime.now());

        service.create(authLog);
    }

    public int failTimeCount(final User user) {
        final var lastSuccessLog = service.findLastSuccessLog(user.getUuid());

        if (lastSuccessLog == null) {
            return service.findAllLastFailLog(user.getUuid(), AuthType.LOGIN).size();

        } else {
            return service.findAllLastFailLog(lastSuccessLog.getCreatedAt(), user.getUuid(), AuthType.LOGIN).size();
        }
    }


}
