package swe_group_11.pethealthmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.PetDTO;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    //DTO로 id, name, species 전달 받아서 repository에 저장 후, 다시 변경된 정보들 표시용으로 return
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setOwnerId(petDTO.getOwnerId());
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        Pet savedPet = petRepository.save(pet);
        return convertToDTO(savedPet);
    }

    //DTO로 변환
    private PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setOwnerId(pet.getOwnerId());
        petDTO.setName(pet.getName());
        petDTO.setSpecies(pet.getSpecies());
        return petDTO;
    }
}
