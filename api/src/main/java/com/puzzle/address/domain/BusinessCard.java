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

@Entity
@Table(name = "business_cards")
@Getter
@Setter
public class BusinessCard {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "address_uuid", nullable = false)
    private String addressUuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rank", nullable = true)
    private String rank;

    @Column(name = "company_name", nullable = true)
    private String companyName;

    @Column(name ="company_url", nullable = true)
    private String companyUrl;

    @Column(name= "company_address", nullable = true)
    private String companyAddress;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
}
