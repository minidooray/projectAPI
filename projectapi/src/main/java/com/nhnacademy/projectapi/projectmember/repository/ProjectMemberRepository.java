package com.nhnacademy.projectapi.projectmember.repository;

import com.nhnacademy.projectapi.projectmember.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository  extends JpaRepository<ProjectMember, Long> {

    Optional<List<ProjectMember>> findByPk_ProjectId(Long id);
    Optional<List<ProjectMember>> findByPk_AccountId(String id);
    Optional<ProjectMember> findByPk_ProjectIdAndPk_AccountId(Long projectId,String accountId);

}
