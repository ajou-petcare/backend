package swe_group_11.pethealthmanager.DTO;

import lombok.Data;

@Data
public class PetDTO {
    private Long id;
    private Long ownerId;
    private String petName;
    private String species;

}

