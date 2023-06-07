package com.nhnacademy.projectapi.task.repository;

import com.nhnacademy.projectapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository<Task, Long> {

    Optional<List<Task>> findByProject_ProjectId(Long id);
}
