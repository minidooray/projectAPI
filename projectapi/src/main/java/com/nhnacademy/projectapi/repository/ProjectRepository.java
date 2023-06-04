package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends JpaRepository<Project, Long> {
}
