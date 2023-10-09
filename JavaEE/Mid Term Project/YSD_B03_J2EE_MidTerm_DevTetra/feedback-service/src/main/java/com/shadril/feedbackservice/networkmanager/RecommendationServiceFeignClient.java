package com.shadril.feedbackservice.networkmanager;

import com.shadril.feedbackservice.dto.RecommendationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "recommendation-service",  configuration = FeignClientConfiguration.class)
public interface RecommendationServiceFeignClient {
    @GetMapping("/recommendations/recommendation/{recommendationId}")
    RecommendationDto getRecommendationByRecommendationId (@PathVariable Long recommendationId);
}
