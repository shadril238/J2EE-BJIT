package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecommendationDto {
    private Long userId;
    private String email;
    private String name;
    private Integer age;
    private String gender;
    private List<RecommendationFeedbackDto> recommendation;
}