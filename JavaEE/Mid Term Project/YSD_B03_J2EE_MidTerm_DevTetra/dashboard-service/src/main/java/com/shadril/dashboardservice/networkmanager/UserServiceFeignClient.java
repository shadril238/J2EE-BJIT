package com.shadril.dashboardservice.networkmanager;

import com.shadril.dashboardservice.dto.HealthDataDto;
import com.shadril.dashboardservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service",  configuration = FeignClientConfiguration.class)

public interface UserServiceFeignClient {
    @GetMapping("/users/healthdata/all")
    List<HealthDataDto> getAllHealthData ();

    @GetMapping("/users/userId/{userId}")
    UserDto userDetailsByUserId(@PathVariable Long userId);

    @GetMapping("/users/healthdata/{userId}")
    List<HealthDataDto> getHealthDataById (@PathVariable Long userId);
}
