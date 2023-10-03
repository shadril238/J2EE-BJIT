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
@Table(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String foodName;
    private String recipe;
}
