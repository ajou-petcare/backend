package swe_group_11.pethealthmanager.service;




import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.*;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.model.User;
import swe_group_11.pethealthmanager.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserRegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public boolean validateCredentials(UserLoginDTO userLoginDTO){
        return userRepository.findByUsername(userLoginDTO.getUsername())
                .map(user -> passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()))
                .orElse(false);
    }

    public UserLoginResponseDTO getUserInfo(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToLoginResponseDTO(user);
    }

    private UserLoginResponseDTO convertToLoginResponseDTO(User user) {
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setPets(user.getPets().stream()
                .map(this::convertPetToDTO)
                .collect(Collectors.toList()));
        return responseDTO;
    }



    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPets(user.getPets().stream()
                .map(this::convertPetToDTO)
                .collect(Collectors.toList()));
        return userDTO;
    }

    private PetDTO convertPetToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setSpecies(pet.getSpecies());
        // 기타 필요한 필드 설정
        return petDTO;
    }


}
