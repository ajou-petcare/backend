package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swe_group_11.pethealthmanager.DTO.FoodRecommendationDTO;
import swe_group_11.pethealthmanager.service.FoodRecommendationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/food_recommendations")
public class FoodRecommendationController {

    private FoodRecommendationService foodRecommendationService;

    @PostMapping
    public ResponseEntity<?> createFoodRecommendation(@RequestBody FoodRecommendationDTO recommendationDTO) {
        FoodRecommendationDTO createdRecommendation = foodRecommendationService.createFoodRecommendation(recommendationDTO);
        return ResponseEntity.ok(createdRecommendation);
    }


}
