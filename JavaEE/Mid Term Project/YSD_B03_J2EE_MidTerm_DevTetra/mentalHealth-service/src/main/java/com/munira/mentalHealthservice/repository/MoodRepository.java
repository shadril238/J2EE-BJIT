package com.munira.mentalHealthservice.repository;

import com.munira.mentalHealthservice.entity.MoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodRepository extends JpaRepository<MoodEntity, Long> {
    List<MoodEntity> findAllByUserId(Long userId);

    MoodEntity findTopByUserIdOrderByTimeStampDesc(Long userId);
}
