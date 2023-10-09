package com.example.recommendationservice.networkmanager;

import com.example.recommendationservice.dto.HealthDataDto;
import com.example.recommendationservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignClientConfiguration.class)
public interface UserFiengClient {
    @GetMapping("/users/{email}")
    UserDto userDetailsByEmail(@PathVariable String email);

    @GetMapping("/users/healthdata")
    HealthDataDto getAllHealthData ();

}
