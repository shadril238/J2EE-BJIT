package com.munira.mentalHealthservice.repository;

import com.munira.mentalHealthservice.entity.MentalHealthExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentalHealthExerciseRepository extends JpaRepository<MentalHealthExerciseEntity, Long> {
    List<MentalHealthExerciseEntity> findByExerciseScore(int moodScore);
}
