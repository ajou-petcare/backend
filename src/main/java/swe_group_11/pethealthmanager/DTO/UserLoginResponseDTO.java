package swe_group_11.pethealthmanager.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginResponseDTO {
    private String username;
    private List<PetDTO> pets;
}
