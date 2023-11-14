package swe_group_11.pethealthmanager.service;




import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.UserDTO;
import swe_group_11.pethealthmanager.DTO.UserRegisterDTO;
import swe_group_11.pethealthmanager.model.User;
import swe_group_11.pethealthmanager.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    public boolean validateCredentials(String username, String password){
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(user1 -> passwordEncoder.matches(password, user1.getPassword())).orElse(false);
    }




    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
