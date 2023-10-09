package com.example.userservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "health_data")
public class HealthDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long healthDataId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
    private Double height;
    private Double weight;
    private Double sleepTime;
    private String bloodPressure;
    private Double bmi;
    private Double bmr;
    private LocalDateTime timestamp;


}
