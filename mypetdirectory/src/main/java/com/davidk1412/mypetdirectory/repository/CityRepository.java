package com.davidk1412.mypetdirectory.repository;

import com.davidk1412.mypetdirectory.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
}
