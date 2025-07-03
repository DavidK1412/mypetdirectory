package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_verification_tokens")
public class EmailVerificationToken {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String token;

    private LocalDateTime expiresAt;

    private Boolean used = false;
}
