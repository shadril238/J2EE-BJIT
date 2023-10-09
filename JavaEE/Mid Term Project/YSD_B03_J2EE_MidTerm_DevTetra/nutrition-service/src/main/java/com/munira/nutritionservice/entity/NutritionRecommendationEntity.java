package com.munira.nutritionservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "nutrition_recommendation")
public class NutritionRecommendationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionRecommendationId;

    private String nutritionRecommendationType;
    private String nutritionRecommendationText;
}
