package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.*;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.model.User;
import swe_group_11.pethealthmanager.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO registerUser(UserRegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public boolean validateCredentials(UserLoginDTO loginDTO) {
        return userRepository.findByUsername(loginDTO.getUsername())
                .map(user -> user.getPassword().equals(loginDTO.getPassword()))
                .orElse(false);
    }

    public UserDTO getUserInfo(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        // null 검사를 추가하여 안전하게 펫 목록을 설정
        List<PetDTO> petDTOs = Optional.ofNullable(user.getPets())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertPetToDTO)
                .collect(Collectors.toList());
        userDTO.setPets(petDTOs);

        return userDTO;
    }

    private PetDTO convertPetToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setPetName(pet.getPetName());
        petDTO.setSpecies(pet.getSpecies());
        return petDTO;
    }
}

