package com.munira.nutritionservice.service.implementation;

import com.munira.nutritionservice.dto.NutritionRecommendationDTO;
import com.munira.nutritionservice.entity.NutritionRecommendationEntity;
import com.munira.nutritionservice.repository.NutritionRecommendationRepository;
import com.munira.nutritionservice.service.NutritionRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionRecommendationServiceImpl implements NutritionRecommendationService {

    @Autowired
    private NutritionRecommendationRepository nutritionRecommendationRepository;

    public void createNutritionRecommendation(NutritionRecommendationDTO nutritionRecommendationDTO){
        NutritionRecommendationEntity nutritionRecommendationEntity = new NutritionRecommendationEntity();
        nutritionRecommendationEntity.setNutritionRecommendationType(nutritionRecommendationDTO.getNutritionRecommendationType());
        nutritionRecommendationEntity.setNutritionRecommendationText(nutritionRecommendationDTO.getNutritionRecommendationText());

        nutritionRecommendationRepository.save(nutritionRecommendationEntity);
    }

}
