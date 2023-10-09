package com.nuri.socialservice.repository;

import com.nuri.socialservice.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower,Long> {

    Optional<Follower> findByFollowerUserIdAndUserId(Long followerId, Long id);
    Long countByFollowerUserId(Long userId);
}
