package swe_group_11.pethealthmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swe_group_11.pethealthmanager.model.Pet;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByPetId(String petId);

}
