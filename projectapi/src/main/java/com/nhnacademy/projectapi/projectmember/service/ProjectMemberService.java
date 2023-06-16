package com.nhnacademy.projectapi.projectmember.service;

import com.nhnacademy.projectapi.exception.NotFoundException;
import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.project.repository.ProjectRepository;
import com.nhnacademy.projectapi.projectmember.dto.ProjectMemberAccountDTO;
import com.nhnacademy.projectapi.projectmember.dto.ProjectMemberProjectDTO;
import com.nhnacademy.projectapi.projectmember.entity.ProjectMember;
import com.nhnacademy.projectapi.projectmember.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMemberAccountDTO> getMembers(Long id){
        List<ProjectMember> list = projectMemberRepository.findByPk_ProjectId(id).orElseThrow();
        return list.stream()
                .map(m -> new ProjectMemberAccountDTO(m.getPk().getAccountId()))
                .collect(Collectors.toList());
    }
    public List<ProjectMemberProjectDTO> getMemberByProject(String loginId){
        List<ProjectMember> list = projectMemberRepository.findByPk_AccountId(loginId).orElseThrow();
        return list.stream()
                .map(m -> new ProjectMemberProjectDTO(m.getPk().getProjectId()))
                .collect(Collectors.toList());
    }
    public void registerMember(Long id, String loginId){
        Project project = projectRepository.findById(id).orElseThrow(()->new NotFoundException("프로젝트가 없습니다."));
        ProjectMember projectMember = new ProjectMember().builder()
                .pk(new ProjectMember.Pk(loginId,id))
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);
    }
    public void deleteMember(Long projectId,String loginId){
        ProjectMember member = projectMemberRepository.findByPk_ProjectIdAndPk_AccountId(projectId,loginId)
                .orElseThrow(()->new NotFoundException("멤버가 없습니다."));
        projectMemberRepository.delete(member);
    }
}
