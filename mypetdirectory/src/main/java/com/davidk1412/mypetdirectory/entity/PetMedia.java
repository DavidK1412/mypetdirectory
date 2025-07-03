package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pet_media")
public class PetMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type;

    private LocalDateTime uploadedAt = LocalDateTime.now();
}

