package com.shadril.dashboardservice.networkmanager;

import com.shadril.dashboardservice.dto.RecommendationFeedbackDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "recommendation-service",  configuration = FeignClientConfiguration.class)
public interface RecommendationServiceFeignClient {
    @GetMapping("/recommendations/recommendation/{recommendationId}")
    RecommendationFeedbackDto getRecommendationByRecommendationId (@PathVariable Long recommendationId);
}
