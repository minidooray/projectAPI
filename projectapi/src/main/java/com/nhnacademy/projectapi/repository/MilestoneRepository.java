package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository  extends JpaRepository<Milestone, Long> {
}
