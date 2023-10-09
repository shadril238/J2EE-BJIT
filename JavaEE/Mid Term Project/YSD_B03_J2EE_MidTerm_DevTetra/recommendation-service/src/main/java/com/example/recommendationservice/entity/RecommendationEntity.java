package com.example.recommendationservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "recommendation")
public class RecommendationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;
    private String recommendationType;
    private String recommendationCategory;

    @Column(columnDefinition = "LONGTEXT")
    private String recommendationText;

}
