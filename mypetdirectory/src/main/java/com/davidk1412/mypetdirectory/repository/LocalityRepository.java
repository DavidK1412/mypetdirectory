package com.davidk1412.mypetdirectory.repository;

import com.davidk1412.mypetdirectory.entity.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalityRepository extends JpaRepository<Locality, Integer> {
    List<Locality> findByCityId(Integer cityId);
}
