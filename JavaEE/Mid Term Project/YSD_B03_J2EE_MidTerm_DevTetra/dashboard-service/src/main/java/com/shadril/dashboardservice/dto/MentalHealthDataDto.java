package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentalHealthDataDto {
    private Long id;
    private String moodState;
    private String stateType;
}
