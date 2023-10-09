package com.example.recommendationservice.service.implementation;


import com.example.recommendationservice.dto.HealthDataDto;
import com.example.recommendationservice.dto.RecommendationDto;
import com.example.recommendationservice.dto.UserDto;
import com.example.recommendationservice.entity.RecommendationEntity;
import com.example.recommendationservice.networkmanager.MentalHealthFiengClient;
import com.example.recommendationservice.networkmanager.UserFiengClient;
import com.example.recommendationservice.repository.RecommendationRepository;
import com.example.recommendationservice.service.RecommendationService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecommendationServiceImplementation implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserFiengClient userFiengClient;

    @Autowired
    private MentalHealthFiengClient mentalHealthFiengClient;

    @Override
    public void createRecommendations(List<RecommendationDto> recommendationDto) {
        List<RecommendationEntity> recommendationEntity = new ArrayList<>();
        for (RecommendationDto recommendationDto1 : recommendationDto) {
            recommendationEntity.add(new ModelMapper().map(recommendationDto1, RecommendationEntity.class));
        }
        recommendationRepository.saveAll(recommendationEntity);
    }

    public Optional <RecommendationDto> getRecommendationByRecommendationId (Long recommendationId) throws Exception{
        Optional <RecommendationEntity> recommendationEntity = recommendationRepository.findById(recommendationId);
        if (recommendationEntity.isPresent()) {
            return Optional.of(new ModelMapper().map(recommendationEntity, RecommendationDto.class));
        } else {
            return Optional.empty();
        }
    }

    public  List<RecommendationDto> getDietRecommendations() {
        String bmiCategory = bmiCategory();
        List<RecommendationEntity> recommendationEntities = recommendationRepository.findAllByRecommendationTypeAndRecommendationCategory(bmiCategory, "diet");
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        for (RecommendationEntity recommendationEntity: recommendationEntities) {
            recommendationDtos.add(new ModelMapper().map(recommendationEntity, RecommendationDto.class));
        }
        return recommendationDtos;
    }

    public  List<RecommendationDto> getExerciseRecommendations() {
        String bmiCategory = bmiCategory();
        List<RecommendationEntity> recommendationEntities = recommendationRepository.findAllByRecommendationTypeAndRecommendationCategory(bmiCategory, "exercise");
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        for (RecommendationEntity recommendationEntity: recommendationEntities) {
            recommendationDtos.add(new ModelMapper().map(recommendationEntity, RecommendationDto.class));
        }
        return recommendationDtos;
    }

    public List<RecommendationDto> getMentalHealthRecommendations() {
        String moodCategory = mentalHealthFiengClient.getLastMood().getMoodState();
        List<RecommendationEntity> recommendationEntities = recommendationRepository.findAllByRecommendationTypeAndRecommendationCategory(moodCategory, "mentalHealth");
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        for (RecommendationEntity recommendationEntity: recommendationEntities) {
            recommendationDtos.add(new ModelMapper().map(recommendationEntity, RecommendationDto.class));
        }
        return recommendationDtos;
    }

    public List<RecommendationDto> getSleepRecommendations()
    {
        String sleepCategory = sleepCategory();
        List<RecommendationEntity> recommendationEntities = recommendationRepository.findAllByRecommendationTypeAndRecommendationCategory(sleepCategory, "sleep");
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        for (RecommendationEntity recommendationEntity: recommendationEntities) {
            recommendationDtos.add(new ModelMapper().map(recommendationEntity, RecommendationDto.class));
        }
        return recommendationDtos;
    }




    private String bmiCategory() {
        Double bmi = getUserHealthData().getBmi();
        if (bmi < 18.5) {
            return "underWeight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "normalWeight";
        } else {
            return "overWeight";
        }
    }

    private String sleepCategory() {
        Double sleepTime = getUserHealthData().getSleepTime();
        if (sleepTime < 7) {
            return "underSleep";
        } else if (sleepTime >= 7 && sleepTime <= 8) {
            return "normalSleep";
        } else {
            return "overSleep";
        }
    }

    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userFiengClient.userDetailsByEmail(userMail);
    }
    private HealthDataDto getUserHealthData() {
        return userFiengClient.getAllHealthData();
    }
}
