package com.puzzle.iam.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_tokens")
@Getter
@Setter
public class UserToken {
    @Id
    @Column(name = "uuid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    @Column(name = "user_token", nullable = false)
    private String userToken = UUID.randomUUID().toString();
}
