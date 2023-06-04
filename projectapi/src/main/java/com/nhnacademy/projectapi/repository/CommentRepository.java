package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
