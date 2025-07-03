package com.davidk1412.mypetdirectory.repository;

import com.davidk1412.mypetdirectory.entity.PetMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PetMediaRepository extends JpaRepository<PetMedia, Long> {
    List<PetMedia> findByPet_Id(UUID petId);
}
