package com.munira.nutritionservice.controller;

import com.munira.nutritionservice.dto.*;
import com.munira.nutritionservice.exception.DietRecommendationNotFoundException;
import com.munira.nutritionservice.service.DietRecommendationService;
import com.munira.nutritionservice.service.FoodService;
import com.munira.nutritionservice.service.NutritionRecommendationService;
import com.munira.nutritionservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private NutritionService nutritionService;

    @Autowired
    private NutritionRecommendationService nutritionRecommendationService;

    @Autowired
    private DietRecommendationService dietRecommendationService;

    @GetMapping("/search/{foodName}")
    public ResponseEntity<?> searchRecipeFoodByName(@PathVariable String foodName) {
        try {
            FoodDTO food = foodService.findFoodByName(foodName);
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/details/{foodName}")
    public ResponseEntity<?> searchDetailsByFoodName(@PathVariable String foodName) {
        try {
            NutritionDTO nutrition = nutritionService.findNutritionFoodByName(foodName);
            return new ResponseEntity<>(nutrition, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<?> getDietaryRecommendations() {
        try {
            String dietRecommendations = dietRecommendationService.generateDietRecommendation();
            if (dietRecommendations == null) {
                throw new DietRecommendationNotFoundException("No dietary recommendations available.");
            }
            return new ResponseEntity<>(dietRecommendations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recommendations")
    public ResponseEntity<?> addNutritionRecommendations(@RequestBody NutritionRecommendationDTO nutritionRecommendationDTO) {
        try {
            nutritionRecommendationService.createNutritionRecommendation(nutritionRecommendationDTO);
            return new ResponseEntity<>("Recommendation added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
