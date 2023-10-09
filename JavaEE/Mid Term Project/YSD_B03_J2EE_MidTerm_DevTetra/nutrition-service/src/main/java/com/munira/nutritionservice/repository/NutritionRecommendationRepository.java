package com.munira.nutritionservice.repository;

import com.munira.nutritionservice.entity.NutritionRecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRecommendationRepository  extends JpaRepository<NutritionRecommendationEntity, Long> {
    NutritionRecommendationEntity findByNutritionRecommendationType(String bmiCategory);
}
