package swe_group_11.pethealthmanager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDTO {
    private String petName;
    private String base64Image;

}
