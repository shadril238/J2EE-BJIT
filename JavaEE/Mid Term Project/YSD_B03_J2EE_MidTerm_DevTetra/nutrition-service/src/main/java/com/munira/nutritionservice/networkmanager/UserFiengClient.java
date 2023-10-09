package com.munira.nutritionservice.networkmanager;

import com.munira.nutritionservice.dto.HealthDataDTO;
import com.munira.nutritionservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignClientConfiguration.class)
public interface UserFiengClient {
    @GetMapping("/users/{email}")
    UserDTO userDetailsByEmail(@PathVariable String email);

    @GetMapping("/users/healthdata")
    HealthDataDTO getAllHealthData ();
}
