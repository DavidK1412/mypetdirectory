package com.davidk1412.mypetdirectory.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "colors")
    private List<Pet> pets;
}
