package com.nhnacademy.projectapi.repository;

import com.nhnacademy.projectapi.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository  extends JpaRepository<ProjectMember, Long> {

    Optional<List<ProjectMember>> findByPk_ProjectId(Long id);
    Optional<ProjectMember> findByPk_ProjectIdAndPk_AccountId(Long projectId,String accountId);

}
