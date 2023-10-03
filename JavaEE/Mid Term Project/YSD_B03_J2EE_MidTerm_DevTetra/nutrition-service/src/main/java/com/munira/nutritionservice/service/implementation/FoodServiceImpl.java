package com.munira.nutritionservice.service.implementation;

import com.munira.nutritionservice.constants.AppConstants;
import com.munira.nutritionservice.dto.FoodDTO;
import com.munira.nutritionservice.dto.UserDTO;
import com.munira.nutritionservice.entity.FoodEntity;
import com.munira.nutritionservice.exception.FoodNotFoundException;
import com.munira.nutritionservice.networkmanager.UserFiengClient;
import com.munira.nutritionservice.repository.FoodRepository;
import com.munira.nutritionservice.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserFiengClient userFiengClient;

    @Override
    public FoodDTO findFoodByName(String foodName) throws FoodNotFoundException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        UserDTO userDTO = userFiengClient.userDetailsByEmail(userMail);

        System.out.println(userDTO);

        FoodEntity foodEntity = foodRepository
                .findByFoodName(foodName)
                .orElseThrow(()->
                        new FoodNotFoundException(AppConstants.FOOD_NOTFOUND))
                ;
        return new ModelMapper().map(foodEntity, FoodDTO.class);
    }

//    private UserEntity getCurrentUser() throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return userRepository.findByEmail(authentication.getName())
//                .orElseThrow(() -> new Exception(AppConstants.TOKEN_INVALID));
//    }

}
