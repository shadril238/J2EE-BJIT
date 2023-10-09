package com.shadril.feedbackservice.service.implementation;

import com.shadril.feedbackservice.dto.*;
import com.shadril.feedbackservice.networkmanager.MentalHealthServiceFeignClient;
import com.shadril.feedbackservice.networkmanager.UserServiceFeignClient;
import com.shadril.feedbackservice.service.ProgressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgressServiceImplementation implements ProgressService {
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    private MentalHealthServiceFeignClient mentalHealthServiceFeignClient;

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

    // Get all mental health data from the mental health service feign client.
    private List<MentalHealthDataDto> getAllMentalHealthData() {
        return mentalHealthServiceFeignClient.getMood();
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

    // Track BMI insights.
    private InsightsDto trackBmiInsights(HealthDataDto currentData, HealthDataDto previousData) {
        double currentBmi = calculateBmi(currentData);
        double previousBmi = previousData != null ? calculateBmi(previousData) : currentBmi;

        double bmiChangePercentage = calculatePercentageChange(previousBmi, currentBmi);

        InsightsDto bmiInsight = new InsightsDto();
        bmiInsight.setInsightCategory("Health Data - BMI");
        bmiInsight.setPercentage(bmiChangePercentage);
        bmiInsight.setTimestamp(currentData.getTimestamp());

        if (bmiChangePercentage > 0) {
            bmiInsight.setInsightMessage("Your BMI has increased by " + Math.abs(bmiChangePercentage) + "%. Consider adjustments in your diet and exercise routine.");
        }
        else if (bmiChangePercentage == 0) {
            bmiInsight.setInsightMessage("Your BMI has not changed. ");
        }
        else {
            bmiInsight.setInsightMessage("Your BMI has decreased by " + Math.abs(bmiChangePercentage) + "%. Great job maintaining a healthy BMI!");
        }

        return bmiInsight;
    }

    // Calculate blood pressure insights.
    private InsightsDto trackBloodPressureInsights(HealthDataDto currentHealthData, HealthDataDto previousHealthData) {
        InsightsDto bloodPressureInsight = new InsightsDto();
        bloodPressureInsight.setInsightCategory("Health Data - Blood Pressure");

        // Parse the current blood pressure string into systolic and diastolic values.
        String[] currentBloodPressureArray = currentHealthData.getBloodPressure().split("/");
        int currSystolic = Integer.parseInt(currentBloodPressureArray[0]);
        int currDiastolic = Integer.parseInt(currentBloodPressureArray[1]);

        // Parse the previous blood pressure string into systolic and diastolic values.
        String[] prevBloodPressureArray = currentHealthData.getBloodPressure().split("/");
        int prevSystolic = Integer.parseInt(prevBloodPressureArray[0]);
        int prevDiastolic = Integer.parseInt(prevBloodPressureArray[1]);
        
        // Calculate percentage change for systolic and diastolic values.
        double percentageChangeSystolic = calculatePercentageChange(currSystolic, prevSystolic);
        double percentageChangeDiastolic = calculatePercentageChange(currDiastolic, prevDiastolic);
        
        // Set the insight message based on the percentage change for both systolic and diastolic blood pressure.
        String insightMessage = getString(percentageChangeSystolic, percentageChangeDiastolic);

        // Calculate the average percentage change for blood pressure.
        double averagePercentageChange = (percentageChangeSystolic + percentageChangeDiastolic) / 2;

        bloodPressureInsight.setPercentage(averagePercentageChange);
        bloodPressureInsight.setInsightMessage(insightMessage.trim());
        bloodPressureInsight.setTimestamp(currentHealthData.getTimestamp());

        return bloodPressureInsight;
    }

    // Get the insight message based on the percentage change for both systolic and diastolic blood pressure.
    private static String getString(double percentageChangeSystolic, double percentageChangeDiastolic) {
        String insightMessage = "";

        // Systolic
        if (percentageChangeSystolic > 0) {
            insightMessage += "Your systolic blood pressure has increased by " + Math.abs(percentageChangeSystolic) + "%. ";
        }
        else if (percentageChangeSystolic < 0){
            insightMessage += "Your systolic blood pressure has decreased by " + Math.abs(percentageChangeSystolic) + "%. ";
        }
        else {
            insightMessage += "Your systolic blood pressure has not changed. ";
        }
        // Diastolic
        if (percentageChangeDiastolic > 0) {
            insightMessage += "Your diastolic blood pressure has increased by " + Math.abs(percentageChangeDiastolic) + "%. ";
        }
        else if (percentageChangeDiastolic < 0){
            insightMessage += "Your diastolic blood pressure has decreased by " + Math.abs(percentageChangeDiastolic) + "%. ";
        }
        else{
            insightMessage += "Your diastolic blood pressure has not changed. ";
        }

        return insightMessage;
    }

    // Calculate sleep time insights.
    private InsightsDto trackSleepTimeInsights(HealthDataDto currentHealthData, HealthDataDto previousHealthData) {
        InsightsDto sleepTimeInsight = new InsightsDto();
        sleepTimeInsight.setInsightCategory("Health Data - Sleep Time");

        double percentageChangeSleepTime = calculatePercentageChange(currentHealthData.getSleepTime(), previousHealthData.getSleepTime());

        sleepTimeInsight.setPercentage(percentageChangeSleepTime);
        sleepTimeInsight.setTimestamp(currentHealthData.getTimestamp());

        if (percentageChangeSleepTime > 0) {
            sleepTimeInsight.setInsightMessage("Your sleep time has increased by " + Math.abs(percentageChangeSleepTime) + "%. Great job maintaining a healthy sleep schedule!");
        }
        else if (percentageChangeSleepTime < 0) {
            sleepTimeInsight.setInsightMessage("Your sleep time has decreased by " + Math.abs(percentageChangeSleepTime) + "%. Consider adjustments in your sleep schedule.");
        }
        else {
            sleepTimeInsight.setInsightMessage("Your sleep time has not changed. ");
        }

        return sleepTimeInsight;
    }


    // Calculate the progress score based on BMI, heart rate, sleep time, and mental health.
    private Double calculateProgressScore(List<HealthDataDto> healthDataList, List<MentalHealthDataDto> mentalHealthDataList) {
        double progressScore = 0.0;

        for (HealthDataDto healthData : healthDataList) {

            double bmi = calculateBmi(healthData);

            String[] bloodPressureArray = healthData.getBloodPressure().split("/");
            int systolic = Integer.parseInt(bloodPressureArray[0]);
            int diastolic = Integer.parseInt(bloodPressureArray[1]);

            double sleepTime = healthData.getSleepTime();

            // Calculate the progress score based on the health data.
            double dailyHealthScore = (bmi * 0.5) + (systolic * 0.2) + (diastolic * 0.2) + (sleepTime * 0.1);

            progressScore += dailyHealthScore;
        }
        // now average the progress score by entry
        double avgProgressScore = progressScore / healthDataList.size();

        return (Double) avgProgressScore;
    }

    // Generate progress message based on the progress score.
    private String generateProgressMessage(double avgProgressScore) {
        String progressMessage;

        // Threshold
        double healthyThreshold = 80.0;
        double warningThreshold = 60.0;

        if (avgProgressScore >= healthyThreshold) {
            progressMessage = "Your health is in great shape! Keep up the good work.";
        } else if (avgProgressScore >= warningThreshold) {
            progressMessage = "Your health is generally good, but there's room for improvement.";
        } else {
            progressMessage = "Your health needs attention. Consider consulting a healthcare professional.";
        }

        return progressMessage;
    }


    // Track the progress of the user.
    @Override
    public Optional<ProgressTrackDto> trackProgress() {
        ProgressTrackDto progressTrack = new ProgressTrackDto();
        TrackingDataDto trackingData = new TrackingDataDto();
        progressTrack.setUserId(getCurrentUserId());
        trackingData.setHealthData(getAllHealthDataWithBmi());
        trackingData.setMentalHealthData(getAllMentalHealthData());
        progressTrack.setTrackingData(trackingData);

        return Optional.of(progressTrack);
    }

    // Track the insights of the user.
    @Override
    public Optional<ProgressInsightsDto> progressInsights() {
        // Retrieve all health data
        List<HealthDataDto> healthDataList = getAllHealthData();

        ProgressInsightsDto progressInsight = new ProgressInsightsDto();
        progressInsight.setUserId(getCurrentUserId());
        List<InsightsDto> insightsList = new ArrayList<>();

        for (int i = 1; i < healthDataList.size(); i++) {
            HealthDataDto currentHealthData = healthDataList.get(i);
            HealthDataDto previousHealthData = healthDataList.get(i - 1);

            // Bmi insights
            InsightsDto bmiInsight = trackBmiInsights(currentHealthData, previousHealthData);
            insightsList.add(bmiInsight);

            // Blood pressure insights
            InsightsDto bloodPressureInsight = trackBloodPressureInsights(currentHealthData, previousHealthData);
            insightsList.add(bloodPressureInsight);

            // Sleep time insights
            InsightsDto sleepTimeInsight = trackSleepTimeInsights(currentHealthData, previousHealthData);
            insightsList.add(sleepTimeInsight);

        }
        // Progress score insights
        double progressScore = calculateProgressScore(healthDataList, null);
        ProgressDto progressDto = new ProgressDto();
        progressDto.setProgressScore(progressScore);
        progressDto.setProgressMessage(generateProgressMessage(progressScore));

        progressInsight.setProgress(progressDto);
        progressInsight.setInsights(insightsList);
        return Optional.of(progressInsight);
    }
}
