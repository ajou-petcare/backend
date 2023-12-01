package swe_group_11.pethealthmanager.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;
}