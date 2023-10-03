package com.munira.nutritionservice.service;

import com.munira.nutritionservice.dto.NutritionDTO;
import com.munira.nutritionservice.exception.FoodNotFoundException;

public interface NutritionService {
    NutritionDTO findNutritionFoodByName(String foodName) throws FoodNotFoundException;
}
