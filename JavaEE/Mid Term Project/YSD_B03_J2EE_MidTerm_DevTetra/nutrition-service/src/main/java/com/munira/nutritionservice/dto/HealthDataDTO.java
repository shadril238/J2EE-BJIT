package com.munira.nutritionservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HealthDataDTO {
    private Long healthDataId;
    private Long userId;
    private Double height;
    private Double weight;
    private Double sleepTime;
    private String bloodPressure;
    private Double bmi;
    private Double bmr;
    private LocalDateTime timestamp;
}
