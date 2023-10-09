package com.nuri.socialservice.repository;

import com.nuri.socialservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostId(Long postId);
}
