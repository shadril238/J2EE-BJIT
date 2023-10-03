package com.example.userservice.repository;

import com.example.userservice.Entity.HealthDataEntity;
import com.example.userservice.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthDataRepository extends JpaRepository<HealthDataEntity, Long> {
    HealthDataEntity findTopByUserEntityOrderByTimestampDesc(UserEntity currentUser);
    List<HealthDataEntity> findAllByUserEntity(UserEntity currentUser);
}
