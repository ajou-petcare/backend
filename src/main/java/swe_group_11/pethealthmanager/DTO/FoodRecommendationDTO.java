package swe_group_11.pethealthmanager.DTO;

import lombok.Data;

@Data
public class FoodRecommendationDTO {
    private Long id;
    private Long petId;
    private String recommendation;

}