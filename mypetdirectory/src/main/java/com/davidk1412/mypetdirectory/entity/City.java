package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Locality> localities;
}
