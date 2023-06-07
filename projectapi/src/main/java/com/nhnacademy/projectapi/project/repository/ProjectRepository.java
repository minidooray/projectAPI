package com.nhnacademy.projectapi.project.repository;

import com.nhnacademy.projectapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends JpaRepository<Project, Long> {
}
