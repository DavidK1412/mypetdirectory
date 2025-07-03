package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "localities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"city_id", "name"})
})
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "locality")
    private List<Pet> pets;
}

