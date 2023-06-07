package com.nhnacademy.projectapi.comment.repository;

import com.nhnacademy.projectapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByTask_TaskId(Long id);
}
