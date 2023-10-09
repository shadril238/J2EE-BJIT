package com.munira.nutritionservice.repository;

import com.munira.nutritionservice.entity.NutritionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutritionRepository extends JpaRepository<NutritionEntity, Long> {
    Optional<NutritionEntity> findByFoodFoodName(String foodName);

    List<NutritionEntity> findAllByOrderByCaloriesDesc();
}
