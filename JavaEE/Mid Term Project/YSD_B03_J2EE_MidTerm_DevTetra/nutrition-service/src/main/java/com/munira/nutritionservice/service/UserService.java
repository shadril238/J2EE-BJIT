package com.munira.nutritionservice.service;

import com.munira.nutritionservice.dto.HealthDataDTO;
import com.munira.nutritionservice.dto.UserDTO;

public interface UserService {
    UserDTO getCurrentUser() throws Exception;
    HealthDataDTO getUsersHealthData() throws Exception;
}
