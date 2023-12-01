package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe_group_11.pethealthmanager.DTO.UserDTO;
import swe_group_11.pethealthmanager.DTO.UserLoginDTO;
import swe_group_11.pethealthmanager.DTO.UserLoginResponseDTO;
import swe_group_11.pethealthmanager.DTO.UserRegisterDTO;
import swe_group_11.pethealthmanager.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserDTO createdUser = userService.registerUser(userRegisterDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginDTO loginDTO = new UserLoginDTO(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        boolean isAuthentic = userService.validateCredentials(loginDTO);
        if (isAuthentic) {
            UserLoginResponseDTO userInfo = userService.getUserInfo(userLoginDTO.getUsername());
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
