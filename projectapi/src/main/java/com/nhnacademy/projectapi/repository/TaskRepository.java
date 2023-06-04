package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task, Long> {
}
