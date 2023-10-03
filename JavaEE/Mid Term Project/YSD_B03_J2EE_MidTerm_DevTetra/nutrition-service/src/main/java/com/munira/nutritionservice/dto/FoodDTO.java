package com.munira.nutritionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private Long foodId;
    private String foodName;
    private String recipe;
}
