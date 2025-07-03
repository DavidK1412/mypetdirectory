package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    private String phoneNumber;

    private Boolean emailVerified = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pet> pets;
}
