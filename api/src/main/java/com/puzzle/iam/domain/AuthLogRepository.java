package com.puzzle.iam.domain;

import com.puzzle.iam.type.AuthType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuthLogRepository extends JpaRepository<AuthLog, String> {
    int countAllByUserUuidAndSuccessIsFalseAndCreatedAtIsAfter(final String userUuid, final LocalDateTime createdAt);

    int countAllByUserUuidAndTypeAndSuccessIsFalseAndCreatedAtIsAfter(final String userUuid, final AuthType type, final LocalDateTime createdAt);

    AuthLog findFirstByUserUuidAndTypeAndSuccessIsFalseOrderByCreatedAtDesc(final String userUuid, final AuthType type);
    List<AuthLog> findAllByUserUuidAndTypeAndSuccessIsFalseOrderByCreatedAtDesc(final String userUuid, final AuthType type);

    AuthLog findFirstByUserUuidAndTypeAndSuccessIsTrueOrderByCreatedAtDesc(final String userUuid, final AuthType type);
}
