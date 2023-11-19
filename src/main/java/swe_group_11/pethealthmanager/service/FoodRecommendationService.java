package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.FoodRecommendationDTO;
import swe_group_11.pethealthmanager.model.FoodRecommendation;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.repository.FoodRecommendationRepository;
import swe_group_11.pethealthmanager.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class FoodRecommendationService {

    private final FoodRecommendationRepository foodRecommendationRepository;
    private final PetRepository petRepository;

    public FoodRecommendationDTO createFoodRecommendation(FoodRecommendationDTO recommendationDTO) {
        Pet pet = petRepository.findById(recommendationDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found")); // Pet 찾기 및 예외 처리
        FoodRecommendation recommendation = new FoodRecommendation();
        recommendation.setPet(pet); // Pet 객체 설정
        recommendation.setRecommendation(recommendationDTO.getRecommendation());
        FoodRecommendation savedRecommendation = foodRecommendationRepository.save(recommendation);
        return convertToDTO(savedRecommendation);
    }

    private FoodRecommendationDTO convertToDTO(FoodRecommendation recommendation) {
        FoodRecommendationDTO recommendationDTO = new FoodRecommendationDTO();
        recommendationDTO.setId(recommendation.getId());
        recommendationDTO.setPetId(recommendation.getPet().getId()); // Pet 객체에서 ID 추출
        recommendationDTO.setRecommendation(recommendation.getRecommendation());
        return recommendationDTO;
    }
}

