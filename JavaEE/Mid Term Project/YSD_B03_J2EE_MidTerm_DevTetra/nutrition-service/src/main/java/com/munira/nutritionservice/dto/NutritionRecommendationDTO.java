package com.munira.nutritionservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NutritionRecommendationDTO {
    private Long nutritionRecommendationId;
    private String nutritionRecommendationType;
    private String nutritionRecommendationText;
}
