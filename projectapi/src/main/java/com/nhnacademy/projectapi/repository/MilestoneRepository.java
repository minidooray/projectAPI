package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository  extends JpaRepository<Milestone, Long> {

    Optional<List<Milestone>> findByProject_ProjectId(Long id);
}
