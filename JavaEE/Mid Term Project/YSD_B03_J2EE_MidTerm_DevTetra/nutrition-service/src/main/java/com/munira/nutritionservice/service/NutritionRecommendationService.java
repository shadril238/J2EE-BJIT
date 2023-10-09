package com.munira.nutritionservice.service;

import com.munira.nutritionservice.dto.NutritionRecommendationDTO;

public interface NutritionRecommendationService {
    void createNutritionRecommendation(NutritionRecommendationDTO nutritionRecommendationDTO) throws Exception;
}
