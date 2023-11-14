package swe_group_11.pethealthmanager.DTO;

import lombok.Data;

@Data
public class PetDTO {
    private Long id;
    private Long ownerId;
    private String name;
    private String species;

}
