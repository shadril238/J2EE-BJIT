package com.spring.securityPractice.repository;

import com.spring.securityPractice.entity.UserEntity;
import com.spring.securityPractice.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserId(String userId);
}
