package com.nuri.socialservice.repository;

import com.nuri.socialservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByGroupId(Long groupId);
    Optional<Post> findByIdAndUserId(Long postId, Long userId);
}
