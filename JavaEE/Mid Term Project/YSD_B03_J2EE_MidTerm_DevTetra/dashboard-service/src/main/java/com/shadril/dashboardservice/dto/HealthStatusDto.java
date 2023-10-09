package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthStatusDto {
    private String bmiStatus;
    private String bloodPressureStatus;
    private String sleepStatus;
}
