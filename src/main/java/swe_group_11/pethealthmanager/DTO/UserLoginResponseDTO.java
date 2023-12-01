package swe_group_11.pethealthmanager.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserLoginResponseDTO {
    private String username;
    private List<PetDTO> pets;
}
