package com.puzzle.iam.domain;

import com.puzzle.iam.type.AuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthLogService {
    private final AuthLogRepository repository;

    public void create(final AuthLog authLog) {
        repository.save(authLog);
    }

    public int countFailTime(final String userUuid, final LocalDateTime createdAt) {
        return repository.countAllByUserUuidAndTypeAndSuccessIsFalseAndCreatedAtIsAfter(userUuid, AuthType.LOGIN, createdAt) + 1;
    }

    public AuthLog findLastFailLog(final String userUuid) {
        return repository.findFirstByUserUuidAndTypeAndSuccessIsFalseOrderByCreatedAtDesc(userUuid, AuthType.LOGIN);
    }

    public List<AuthLog> findAllLastFailLog(final LocalDateTime lastSuccessLogTime, final String userUuid, final AuthType type) {
        final var authLogs =  repository.findAllByUserUuidAndTypeAndSuccessIsFalseOrderByCreatedAtDesc(userUuid, type);

        return authLogs.stream()
                .filter(authLog -> authLog.getCreatedAt().isAfter(lastSuccessLogTime))
                .toList();

    }

    public List<AuthLog> findAllLastFailLog(final String userUuid, final AuthType type) {
       return  repository.findAllByUserUuidAndTypeAndSuccessIsFalseOrderByCreatedAtDesc(userUuid, type);
    }

    public AuthLog findLastSuccessLog(final String userUuid) {
        return repository.findFirstByUserUuidAndTypeAndSuccessIsTrueOrderByCreatedAtDesc(userUuid, AuthType.LOGIN);
    }
}
