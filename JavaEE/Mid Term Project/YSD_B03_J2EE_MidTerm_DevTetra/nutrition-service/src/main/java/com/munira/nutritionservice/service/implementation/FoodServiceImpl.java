package com.munira.nutritionservice.service.implementation;

import com.munira.nutritionservice.dto.FoodDTO;
import com.munira.nutritionservice.entity.FoodEntity;
import com.munira.nutritionservice.exception.FoodNotFoundException;
import com.munira.nutritionservice.repository.FoodRepository;
import com.munira.nutritionservice.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodDTO findFoodByName(String foodName) throws FoodNotFoundException{
        FoodEntity foodEntity = foodRepository
                .findByFoodName(foodName)
                .orElseThrow(()->
                        new FoodNotFoundException("Food does not exits!"))
                ;
        return new ModelMapper().map(foodEntity, FoodDTO.class);
    }
}
