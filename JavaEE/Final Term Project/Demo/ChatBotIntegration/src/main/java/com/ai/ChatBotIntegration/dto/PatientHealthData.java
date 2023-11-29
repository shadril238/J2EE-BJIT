package com.ai.ChatBotIntegration.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class PatientHealthData {
    private String userId;
    private Double heightCm;
    private Double weightKg;
    private Double bloodSugarLevel;
    private Integer heartRate;
    private Double bmi;
    private LocalDate date;
    private Integer sleepHour;
    private Integer age;
    private String bloodPressure;
    private String bloodGroup;
    private Boolean smoke;
}
