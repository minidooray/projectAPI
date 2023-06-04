package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository  extends JpaRepository<TaskTag, Long> {
}
