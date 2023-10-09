package com.shadril.dashboardservice.networkmanager;

import com.shadril.dashboardservice.dto.FeedbackRequestDto;
import com.shadril.dashboardservice.dto.UserFeedbackDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "feedback-service",  configuration = FeignClientConfiguration.class)
public interface FeedbackServiceFeignClient {
    @GetMapping("/feedback/user/{userId}")
    UserFeedbackDto getAllFeedbackByUserId(@PathVariable Long userId);

    @GetMapping("/feedback/all")
    List<FeedbackRequestDto> getAllFeedback();

    @GetMapping("/feedback/getall")
    List<FeedbackRequestDto> getFeedbacks();
}
