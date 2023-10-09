package com.shadril.dashboardservice.service.implementation;

import com.shadril.dashboardservice.dto.*;
import com.shadril.dashboardservice.exception.FeedbackNotFoundException;
import com.shadril.dashboardservice.exception.ProgressNotFoundException;
import com.shadril.dashboardservice.exception.RecommendationNotFoundException;
import com.shadril.dashboardservice.networkmanager.FeedbackServiceFeignClient;
import com.shadril.dashboardservice.networkmanager.RecommendationServiceFeignClient;
import com.shadril.dashboardservice.networkmanager.UserServiceFeignClient;
import com.shadril.dashboardservice.service.DashboardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DashboardServiceImplementation implements DashboardService {
    @Autowired
    private FeedbackServiceFeignClient feedbackServiceFeignClient;

    @Autowired
    private RecommendationServiceFeignClient recommendationServiceFeignClient;

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public UserRecommendationDto recommendationFeedback(Long userId)
            throws RecommendationNotFoundException, FeedbackNotFoundException{
        UserFeedbackDto userFeedbackDto = getUserFeedback(userId);
        return buildUserRecommendation(userFeedbackDto);
    }

    // Track the progress of the user.
    @Override
    public UserProgressTrackDto trackUserProgress(Long userId)
            throws ProgressNotFoundException {
        UserProgressTrackDto progressTrack = new UserProgressTrackDto();
        TrackingDataDto trackingData = new TrackingDataDto();
        progressTrack.setUserId(userId);
        trackingData.setHealthData(getAllHealthDataWithBmi(userId));
        progressTrack.setTrackingData(trackingData);
        return progressTrack;
    }

    @Override
    public List<DashboardRecommendationDto> recommendationDecision(){
        // Retrieve all feedbacks
        List<FeedbackRequestDto> feedbacks = feedbackServiceFeignClient.getFeedbacks();

        // Create a map to store feedback ratings grouped by recommendationId
        Map<Long, List<Double>> ratingsByRecommendation = new HashMap<>();

        // Group feedback ratings by recommendationId
        for (FeedbackRequestDto feedback : feedbacks) {
            Long recommendationId = feedback.getRecommendationId();
            Double rating = feedback.getFeedbackRating();
            ratingsByRecommendation.computeIfAbsent(recommendationId, k -> new ArrayList<>()).add(rating);
        }

        // Calculate the mean rating for each recommendation
        List<DashboardRecommendationDto> dashboardRecommendations = new ArrayList<>();
        for (Map.Entry<Long, List<Double>> entry : ratingsByRecommendation.entrySet()) {
            Long recommendationId = entry.getKey();
            List<Double> ratings = entry.getValue();
            // Get recommendation based on recommendation id
            RecommendationFeedbackDto recommendationFeedbacks = getRecommendationFeedback(recommendationId);
            // Calculate the mean rating (average) for this recommendation
            double meanRating = calculateMeanRating(ratings);
            String decision = makeDecision(meanRating);

            // Create a DashboardRecommendationDto and add it to the list
            DashboardRecommendationDto recommendationDto = new DashboardRecommendationDto();

            recommendationDto.setRecommendationCategory(recommendationFeedbacks.getRecommendationCategory());
            recommendationDto.setRecommendationType(recommendationFeedbacks.getRecommendationType());
            recommendationDto.setRecommendationText(recommendationFeedbacks.getRecommendationText());
            recommendationDto.setRecommendationId(recommendationId);
            recommendationDto.setMeanRating(meanRating);
            recommendationDto.setRecommendationDecision(decision);

            dashboardRecommendations.add(recommendationDto);
        }
        return dashboardRecommendations;
    }
    //Calculate recommendation decision based on the mean rating.
    private String makeDecision(double meanRating) {
        if (meanRating >= 4.5) {
            return "Highly Positive Recommendation";
        } else if (meanRating >= 4.0) {
            return "Positive Recommendation";
        } else if (meanRating >= 3.0) {
            return "Neutral Recommendation";
        } else if (meanRating >= 2.0) {
            return "Negative Recommendation";
        } else {
            return "Strongly Negative Recommendation";
        }
    }
    // Calculate the mean rating.
    private double calculateMeanRating(List<Double> ratings) {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = ratings.stream().mapToDouble(Double::doubleValue).sum();
        return sum / ratings.size();
    }

    // Retrieve user feedback data from the feedback service.
    private UserFeedbackDto getUserFeedback(Long userId) {
        return feedbackServiceFeignClient.getAllFeedbackByUserId(userId);
    }

    // Building UserRecommendationDto from UserFeedbackDto
    private UserRecommendationDto buildUserRecommendation(UserFeedbackDto userFeedbackDto) throws RecommendationNotFoundException{
        UserRecommendationDto userRecommendationDto = new UserRecommendationDto();
        userRecommendationDto.setUserId(userFeedbackDto.getUserId());
        userRecommendationDto.setEmail(userFeedbackDto.getEmail());
        userRecommendationDto.setName(userFeedbackDto.getName());
        userRecommendationDto.setAge(userFeedbackDto.getAge());
        userRecommendationDto.setGender(userFeedbackDto.getGender());

        List<RecommendationFeedbackDto> recommendationFeedbacks = buildRecommendationFeedback(userFeedbackDto);
        userRecommendationDto.setRecommendation(recommendationFeedbacks);

        return userRecommendationDto;
    }

    // Build a list of RecommendationFeedbackDto objects from UserFeedbackDto.
    private List<RecommendationFeedbackDto> buildRecommendationFeedback(UserFeedbackDto userFeedbackDto)
            throws RecommendationNotFoundException{
        List<RecommendationFeedbackDto> recommendationFeedbacks = new ArrayList<>();
        for (FeedbackResponseDto feedbackResponseDto : userFeedbackDto.getFeedbacks()) {
            RecommendationFeedbackDto recommendationFeedbackDto = getRecommendationFeedback(feedbackResponseDto.getRecommendationId());

            if (recommendationFeedbackDto == null) {
                throw new RecommendationNotFoundException("Recommendation not found");
            }

            // Filter feedbacks based on relevant feedbacks.
            List<FeedbackResponseDto> relevantFeedbacks = userFeedbackDto.getFeedbacks()
                    .stream()
                    .filter(feedback -> feedback.getRecommendationId().equals(recommendationFeedbackDto.getRecommendationId()))
                    .peek(feedback -> feedback.setSatisfactionLevel(calculateSatisfactionLevel(feedback.getFeedbackRating())))
                    .collect(Collectors.toList());
            recommendationFeedbackDto.setFeedbacks(relevantFeedbacks);
            recommendationFeedbacks.add(recommendationFeedbackDto);
        }
        return recommendationFeedbacks;
    }

    // Retrieve recommendation feedback data from the recommendation service.
    private RecommendationFeedbackDto getRecommendationFeedback(Long recommendationId) {
        return recommendationServiceFeignClient.getRecommendationByRecommendationId(recommendationId);
    }

    // Calculate the satisfaction level based on the feedback rating
    private String calculateSatisfactionLevel(double feedbackRating) {
        if (feedbackRating >= 4.5) {
            return "Highly Satisfied";
        } else if (feedbackRating >= 3.0) {
            return "Satisfied";
        } else if (feedbackRating >= 2.0) {
            return "Neutral";
        } else {
            return "Dissatisfied";
        }
    }

    // Get all health data from the user service feign client.
    private List<HealthDataDto> getAllHealthDataByUser(Long userId) {
        return userServiceFeignClient.getHealthDataById(userId);
    }

    // Calculate percentage change between two values.
    private double calculatePercentageChange(double currentValue, double previousValue) {
        if (previousValue == 0.0) {
            return 0.0;
        }
        return ((currentValue - previousValue) / previousValue) * 100.0;
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
    private List<HealthDataDto> getAllHealthDataWithBmi(Long userId) {
        List<HealthDataDto> healthDataList = new ArrayList<>();
        for (HealthDataDto healthDataDto :  getAllHealthDataByUser(userId)) {
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


}
