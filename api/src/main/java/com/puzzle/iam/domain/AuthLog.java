package com.puzzle.iam.domain;

import com.puzzle.iam.type.AuthType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_logs")
@Getter
@Setter
public class AuthLog {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = true)
    private String userUuid;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthType type;

    @Column(name = "success", nullable = false)
    private boolean success;

    @Column(name = "ip",nullable = false)
    private String ip;

    @Column(name = "error_message", nullable = true)
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
