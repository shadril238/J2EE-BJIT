package com.example.recommendationservice.networkmanager;

import com.example.recommendationservice.dto.HealthDataDto;
import com.example.recommendationservice.dto.MoodDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mentalHealth-service", configuration = FeignClientConfiguration.class)
public interface MentalHealthFiengClient {


    @GetMapping("/mental-health/mood-tracking/last")
    MoodDto getLastMood ();
}