package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.PetDTO;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.model.User;
import swe_group_11.pethealthmanager.repository.PetRepository;
import swe_group_11.pethealthmanager.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetDTO createPet(PetDTO petDTO) {
        User owner = userRepository.findById(petDTO.getOwnerId()).orElseThrow();
        Pet pet = new Pet();
        pet.setOwner(owner);
        pet.setPetName(petDTO.getPetName());
        pet.setSpecies(petDTO.getSpecies());
        Pet savedPet = petRepository.save(pet);
        return convertToDTO(savedPet);
    }

    private PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setPetName(pet.getPetName());
        petDTO.setSpecies(pet.getSpecies());
        return petDTO;
    }
}

