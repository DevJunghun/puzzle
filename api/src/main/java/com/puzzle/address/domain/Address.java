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
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "group_uuid", nullable = false)
    private String groupUuid;

    @Column(name = "rank", nullable = true)
    private String rank;

    @Column(name = "department", nullable = true)
    private String department;

    @Column(name = "company_name", nullable = true)
    private String companyName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "use_count", nullable = false)
    private long useCount = 0;

    @Column(name = "has_business_card", nullable = false)
    private boolean hasBusinessCard = true;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
}
