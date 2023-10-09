package com.example.recommendationservice.dto;


import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RecommendationDto {
    private Long recommendationId;
    private String recommendationType;
    private String recommendationCategory;
    private String recommendationText;
}
