package com.puzzle.userSetting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mail_settings")
@Getter
@Setter
public class MailSetting {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    @Column(name = "reminde_time", nullable = false)
    private int remindTime = 24*60;

    @Column(name = "reminder_on", nullable = false)
    private boolean reminderOn = true;

    @Column(name = "auto_classification_on", nullable = false)
    private boolean autoClassificationOn = true;
}
