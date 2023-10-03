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
@Table(name = "nutrition")
public class NutritionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionId;

    @OneToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    private String servingSize;
    private String calories;
    private String carb;
    private String protein;
    private String fat;
    private String vitamin;

}
