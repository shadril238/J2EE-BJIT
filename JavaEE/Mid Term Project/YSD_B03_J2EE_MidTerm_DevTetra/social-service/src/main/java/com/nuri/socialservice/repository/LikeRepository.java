package com.nuri.socialservice.repository;

import com.nuri.socialservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Long countByPostId(Long postId);
    List<Like> findAllByPostId(Long postId);
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
