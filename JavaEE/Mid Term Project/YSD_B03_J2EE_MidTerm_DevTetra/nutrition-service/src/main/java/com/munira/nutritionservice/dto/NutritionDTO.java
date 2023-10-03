package com.munira.nutritionservice.dto;

import com.munira.nutritionservice.entity.FoodEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutritionDTO {
    private Long nutritionId;

    private Long foodId;

    private String servingSize;
    private String calories;
    private String carb;
    private String protein;
    private String fat;
    private String vitamin;
}
