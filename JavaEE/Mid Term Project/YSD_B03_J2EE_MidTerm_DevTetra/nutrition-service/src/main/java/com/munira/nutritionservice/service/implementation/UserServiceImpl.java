package com.munira.nutritionservice.service.implementation;


import com.munira.nutritionservice.dto.HealthDataDTO;
import com.munira.nutritionservice.dto.UserDTO;
import com.munira.nutritionservice.exception.HealthDataNotFoundException;
import com.munira.nutritionservice.exception.UserNotFoundException;
import com.munira.nutritionservice.networkmanager.UserFiengClient;
import com.munira.nutritionservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFiengClient userFiengClient;

    public UserDTO getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userMail = authentication.getName();
            return userFiengClient.userDetailsByEmail(userMail);
        } catch (Exception e) {
            throw new UserNotFoundException("Invalid User");
        }
    }

    public HealthDataDTO getUsersHealthData() {
        try {
            return userFiengClient.getAllHealthData();
        } catch (Exception e) {
            throw new HealthDataNotFoundException("Provide health data to get recommendations.");
        }
    }
}
