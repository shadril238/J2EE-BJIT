package com.shadril.feedbackservice.service.implementation;

import com.shadril.feedbackservice.dto.*;
import com.shadril.feedbackservice.networkmanager.UserServiceFeignClient;
import com.shadril.feedbackservice.service.ProgressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProgressServiceImplementation implements ProgressService {
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    // Get the current user from the JWT token and feign client user service.
    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userServiceFeignClient.userDetailsByEmail(userMail);
    }

    // Get the current user id from the JWT token and feign client user service.
    private Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    // Get all health data from the user service feign client.
    private List<HealthDataDto> getAllHealthData() {
        return userServiceFeignClient.getAllHealthData();
    }

    // Calculate BMI from the health data.
    private double calculateBmi(HealthDataDto healthData) {
        double heightInMeters = healthData.getHeight() / 100.0;
        return healthData.getWeight() / (heightInMeters * heightInMeters);
    }

    // Track the user BMI status
    private String trackBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    // Track the blood pressure status
    private String trackBloodPressureStatus(String bloodPressure) {
        String[] bloodPressureArray = bloodPressure.split("/");
        int systolic = Integer.parseInt(bloodPressureArray[0]);
        int diastolic = Integer.parseInt(bloodPressureArray[1]);
        if (systolic < 120 && diastolic < 80) {
            return "Normal";
        } else if ((systolic >= 120 && systolic <= 129) && diastolic < 80) {
            return "Elevated";
        } else if ((systolic >= 130 && systolic <= 139) || (diastolic >= 80 && diastolic <= 89)) {
            return "High blood pressure (Hypertension) stage 1";
        } else {
            return "High blood pressure (Hypertension) stage 2";
        }
    }

    // Track the sleep time status
    private String trackSleepTimeStatus(double sleepTime) {
        if (sleepTime >= 7 && sleepTime <= 9) {
            return "Normal";
        } else if (sleepTime < 7) {
            return "Insufficient";
        } else {
            return "Excessive";
        }
    }

    // Inject the calculated BMI into the health data.
    private List<HealthDataDto> getAllHealthDataWithBmi() {
        List<HealthDataDto> healthDataList = new ArrayList<>();
        for (HealthDataDto healthDataDto : getAllHealthData()) {
            double bmi  = calculateBmi(healthDataDto);
            // Set bmi to health data dto.
            healthDataDto.setBmi(bmi);

            // Set bmi status to health data dto.
            HealthStatusDto healthStatus = new HealthStatusDto();
            healthStatus.setBmiStatus(trackBmiStatus(bmi));
            // Set blood pressure status to health data dto.
            healthStatus.setBloodPressureStatus(trackBloodPressureStatus(healthDataDto.getBloodPressure()));
            // Set sleep time status to health data dto.
            healthStatus.setSleepStatus(trackSleepTimeStatus(healthDataDto.getSleepTime()));

            healthDataDto.setHealthStatus(healthStatus);
            healthDataList.add(healthDataDto);
        }
        return healthDataList;
    }


    @Override
    public ProgressTrackDto trackProgress() {
        ProgressTrackDto progressTrack = new ProgressTrackDto();
        TrackingDataDto trackingData = new TrackingDataDto();
        progressTrack.setUserId(getCurrentUserId());
        trackingData.setHealthData(getAllHealthDataWithBmi());
        progressTrack.setTrackingData(trackingData);
        return progressTrack;
    }
}
