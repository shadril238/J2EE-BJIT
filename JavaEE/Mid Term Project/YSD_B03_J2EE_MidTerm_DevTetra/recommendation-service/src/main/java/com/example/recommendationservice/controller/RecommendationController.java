package com.example.recommendationservice.controller;

import com.example.recommendationservice.dto.RecommendationDto;
import com.example.recommendationservice.entity.RecommendationEntity;
import com.example.recommendationservice.exceptions.RecommendationIdNotFoundException;
import com.example.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommendations/add")
    public ResponseEntity<?> createRecommendations (@RequestBody List<RecommendationDto> recommendationDto) {
        try {
            recommendationService.createRecommendations(recommendationDto);
            return new ResponseEntity<>("Recommendation added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommendations/recommendation/{recommendationId}")
    public ResponseEntity<?> getRecommendationByRecommendationId (@PathVariable Long recommendationId) {
        try {
            Optional<RecommendationDto> recommendationDto = recommendationService.getRecommendationByRecommendationId(recommendationId);
            if (recommendationDto.isEmpty()) throw new RecommendationIdNotFoundException("Recommendation not found by this id!");
            return new ResponseEntity<>(recommendationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommendations/diet")
    public ResponseEntity<?> getDietRecommendations () {
        try {
            List<RecommendationDto> recommendationDto =  recommendationService.getDietRecommendations();
            return new ResponseEntity<>(recommendationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommendations/exercise")
    public ResponseEntity<?> getExerciseRecommendations () {
        try {
            List<RecommendationDto> recommendationDto = recommendationService.getExerciseRecommendations();
            return new ResponseEntity<>(recommendationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/recommendations/mental-health")
    public ResponseEntity<?> getMentalHealthRecommendations () {
        try {
            List<RecommendationDto> recommendationDto = recommendationService.getMentalHealthRecommendations();
            return new ResponseEntity<>(recommendationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/recommendations/sleep")
    public ResponseEntity<?> getSleepRecommendations () {
        try {
            List<RecommendationDto> recommendationDto = recommendationService.getSleepRecommendations();
            return new ResponseEntity<>(recommendationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
