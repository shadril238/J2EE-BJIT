package com.shadril.dashboardservice.controller;

import com.shadril.dashboardservice.dto.DashboardRecommendationDto;
import com.shadril.dashboardservice.dto.UserProgressTrackDto;
import com.shadril.dashboardservice.dto.UserRecommendationDto;
import com.shadril.dashboardservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/feedback/{userId}")
    public ResponseEntity<UserRecommendationDto> recommendationFeedback(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(dashboardService.recommendationFeedback(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/progress/{userId}")
    public ResponseEntity<UserProgressTrackDto> trackUserProgress(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(dashboardService.trackUserProgress(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<DashboardRecommendationDto>> recommendationDecision() {
        try {
            return new ResponseEntity<>(dashboardService.recommendationDecision(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
