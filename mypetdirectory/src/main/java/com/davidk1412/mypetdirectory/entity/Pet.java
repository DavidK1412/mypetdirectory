package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locality_id", nullable = false)
    private Locality locality;

    private String nameOnTag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetType type;

    @Enumerated(EnumType.STRING)
    private PetSize size;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate foundDate;

    private Boolean delivered = false;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "pet_colors",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<Color> colors;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetMedia> media;
}

