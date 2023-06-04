package com.nhnacademy.projectapi.service;

import com.nhnacademy.projectapi.entity.Project;
import com.nhnacademy.projectapi.entity.ProjectMember;
import com.nhnacademy.projectapi.repository.ProjectMemberRepository;
import com.nhnacademy.projectapi.repository.ProjectRepository;
import com.nhnacademy.projectapi.request.ProjectMemberRequestDTO;
import com.nhnacademy.projectapi.response.ProjectMemberResponseDTO;
import com.nhnacademy.projectapi.response.ProjectResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMemberResponseDTO> getMembers(Long id){
        List<ProjectMember> list = projectMemberRepository.findByPk_ProjectId(id).orElseThrow();
        return list.stream()
                .map(m -> new ProjectMemberResponseDTO(m.getPk().getAccountId()))
                .collect(Collectors.toList());
    }

    public void registerMember(Long id, ProjectMemberRequestDTO dto){
        Project project = projectRepository.findById(id).orElseThrow();
        ProjectMember projectMember = new ProjectMember().builder()
                .pk(new ProjectMember.Pk(dto.getAccountId(), dto.getProjectId()))
                .project(project)
                .build();
        projectMemberRepository.saveAndFlush(projectMember);
    }
    public void deleteMember(Long projectId,ProjectMemberRequestDTO dto){
        ProjectMember member = projectMemberRepository.findByPk_ProjectIdAndPk_AccountId(projectId,dto.getAccountId())
                .orElseThrow();
        projectMemberRepository.delete(member);
    }
}
