package com.munira.mentalHealthservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "mental_health_exercise")
public class MentalHealthExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exerciseTitle;
    private String exerciseDescription;
    private String exerciseInstruction;
    private String exerciseDuration;
    private Long exerciseScore;
}
