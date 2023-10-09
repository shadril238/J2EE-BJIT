package com.shadril.feedbackservice.networkmanager;

import com.shadril.feedbackservice.dto.MentalHealthDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "mentalHealth-service",  configuration = FeignClientConfiguration.class)
public interface MentalHealthServiceFeignClient {
    @GetMapping("/mental-health/mood-tracking")
    List<MentalHealthDataDto> getMood();
}
