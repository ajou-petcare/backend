package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.FoodRecommendationDTO;
import swe_group_11.pethealthmanager.model.FoodRecommendation;
import swe_group_11.pethealthmanager.repository.FoodRecommendationRepository;

@Service
@RequiredArgsConstructor
public class FoodRecommendationService {

    private FoodRecommendationRepository foodRecommendationRepository;

    public FoodRecommendationDTO createFoodRecommendation(FoodRecommendationDTO recommendationDTO) {
        FoodRecommendation recommendation = new FoodRecommendation();
        recommendation.setPetId(recommendationDTO.getPetId());
        recommendation.setRecommendation(recommendationDTO.getRecommendation());
        FoodRecommendation savedRecommendation = foodRecommendationRepository.save(recommendation);
        return convertToDTO(savedRecommendation);
    }

    private FoodRecommendationDTO convertToDTO(FoodRecommendation recommendation) {
        FoodRecommendationDTO recommendationDTO = new FoodRecommendationDTO();
        recommendationDTO.setId(recommendation.getId());
        recommendationDTO.setPetId(recommendation.getPetId());
        recommendationDTO.setRecommendation(recommendation.getRecommendation());
        return recommendationDTO;
    }


}
