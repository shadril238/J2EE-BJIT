package com.munira.nutritionservice.controller;

import com.munira.nutritionservice.dto.FoodDTO;
import com.munira.nutritionservice.dto.NutritionDTO;
import com.munira.nutritionservice.service.FoodService;
import com.munira.nutritionservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private NutritionService nutritionService;

    @GetMapping("/search/{foodName}")
    public ResponseEntity<?> searchRecipeFoodByName (@PathVariable String foodName){
        try{
            FoodDTO food = foodService.findFoodByName(foodName);
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/details/{foodName}")
    public ResponseEntity<?> searchDetailsByFoodName (@PathVariable String foodName){
        try{
            NutritionDTO nutrition = nutritionService.findNutritionFoodByName(foodName);
            return new ResponseEntity<>(nutrition, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
