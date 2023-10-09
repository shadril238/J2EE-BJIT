package com.munira.nutritionservice.service.implementation;

import com.munira.nutritionservice.dto.HealthDataDTO;
import com.munira.nutritionservice.entity.NutritionEntity;
import com.munira.nutritionservice.entity.NutritionRecommendationEntity;
import com.munira.nutritionservice.repository.FoodRepository;
import com.munira.nutritionservice.repository.NutritionRecommendationRepository;
import com.munira.nutritionservice.repository.NutritionRepository;
import com.munira.nutritionservice.service.DietRecommendationService;
import com.munira.nutritionservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DietRecommendationServiceImpl implements DietRecommendationService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private NutritionRepository nutritionRepository;

    @Autowired
    private NutritionRecommendationRepository nutritionRecommendationRepository;

    @Autowired
    private UserService userService;

    @Override
    public String generateDietRecommendation() throws Exception {
        HealthDataDTO healthDataDTO = userService.getUsersHealthData();
        double bmr = healthDataDTO.getBmr();
        double bmi = healthDataDTO.getBmi();
        return recommendationString(bmr, bmi);
    }

    private String recommendationString(double bmr, double bmi){

        StringBuilder recommendations = new StringBuilder();

        double dailyCalorieNeeds = calculateDailyCalorieNeeds(bmr);
        String bmiCategory = determineBMICategory(bmi);

        recommendations.append("Your BMR count is: ").append(bmr).append("\n");
        recommendations.append("Your estimated daily calorie need is: ").append(dailyCalorieNeeds).append(" calories.\n");
        recommendations.append("Your BMI count is: ").append(bmi).append("\n");

        NutritionRecommendationEntity nutritionRecommendationEntity = nutritionRecommendationRepository
                .findByNutritionRecommendationType(bmiCategory);
        recommendations.append(nutritionRecommendationEntity.getNutritionRecommendationText()).append("\n");

        recommendations.append("Here are some Food recommendations based on your BMI:\n");

        List<NutritionEntity> nutritionEntities = nutritionRepository.findAllByOrderByCaloriesDesc();

        int startIndex = 0, endIndex = nutritionEntities.size();
        if ("underWeight".equalsIgnoreCase(bmiCategory)) {
            endIndex = nutritionEntities.size() / 2;
        } else if ("normalWeight".equalsIgnoreCase(bmiCategory)) {
            startIndex = nutritionEntities.size() / 4;
            endIndex = (3 * nutritionEntities.size()) / 4;
        } else if ("overWeight".equalsIgnoreCase(bmiCategory)) {
            startIndex = nutritionEntities.size() / 2;
        }
        for (int i = startIndex; i < endIndex; i++) {
            NutritionEntity nutritionEntity = nutritionEntities.get(i);
            String foodName = nutritionEntity.getFood().getFoodName();
            recommendations.append("- ").append(foodName).append(": ").append(nutritionEntity.getCalories()).append(" calories.\n");
        }
        return recommendations.toString();
    }

    private double calculateDailyCalorieNeeds(double bmr) {
        return round(bmr * 1.2);
    }

    private String determineBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "underWeight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "normalWeight";
        } else {
            return "overWeight";
        }
    }

    private double round(double value) {
        long factor = (long) Math.pow(10, 0);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
