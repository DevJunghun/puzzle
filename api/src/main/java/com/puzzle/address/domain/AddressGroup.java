package com.puzzle.address.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "address_groups")
@Getter
@Setter
public class AddressGroup {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "default_group", nullable = false)
    private boolean defaultGroup = true;

    @Column(name = "has_parent_group", nullable = false)
    private boolean hasParentGroup = false;

    @Column(name = "parent_group_uuid", nullable = true)
    private String parentGroupUuid;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

}
