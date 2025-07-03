package com.davidk1412.mypetdirectory.repository;

import com.davidk1412.mypetdirectory.entity.Pet;
import com.davidk1412.mypetdirectory.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID>, JpaSpecificationExecutor<Pet> {
    List<Pet> findByDeliveredFalse();

    List<Pet> findByTypeAndDeliveredFalse(PetType type);

    List<Pet> findByColors_Id(Integer colorId);

    List<Pet> findByLocality_Id(Integer localityId);
}
