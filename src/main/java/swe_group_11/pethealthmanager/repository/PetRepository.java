package swe_group_11.pethealthmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swe_group_11.pethealthmanager.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
