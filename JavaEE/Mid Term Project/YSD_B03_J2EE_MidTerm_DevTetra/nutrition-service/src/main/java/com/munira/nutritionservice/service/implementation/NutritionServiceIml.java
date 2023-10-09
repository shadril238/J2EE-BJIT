package com.munira.nutritionservice.service.implementation;


import com.munira.nutritionservice.dto.NutritionDTO;
import com.munira.nutritionservice.entity.NutritionEntity;
import com.munira.nutritionservice.exception.FoodNotFoundException;
import com.munira.nutritionservice.repository.NutritionRepository;
import com.munira.nutritionservice.service.NutritionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceIml implements NutritionService {
    @Autowired
    private NutritionRepository nutritionRepository;

    public NutritionDTO findNutritionFoodByName(String foodName) throws FoodNotFoundException {
        NutritionEntity nutritionEntity = nutritionRepository
                .findByFoodFoodName(foodName)
                .orElseThrow(()->
                        new FoodNotFoundException("Food does not exits!"))
                ;
        return new ModelMapper().map(nutritionEntity, NutritionDTO.class);
    }
}
