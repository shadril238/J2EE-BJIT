package com.munira.nutritionservice.service;

import com.munira.nutritionservice.dto.FoodDTO;
import com.munira.nutritionservice.exception.FoodNotFoundException;

public interface FoodService {
    FoodDTO findFoodByName(String foodName) throws FoodNotFoundException;
}
