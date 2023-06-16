package com.nhnacademy.projectapi.project.service;

import com.nhnacademy.projectapi.ProjectStatus;
import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.exception.NotFoundException;
import com.nhnacademy.projectapi.project.dto.ProjectRequestDTO;
import com.nhnacademy.projectapi.project.dto.ProjectResponseDTO;
import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ProjectResponseDTO> getProjects(){
        List<Project> list = projectRepository.findAll();
        return list.stream()
                .map(m -> new ProjectResponseDTO(m.getProjectId(),
                        m.getProjectAdminId(),
                        m.getProjectName(),
                        m.getProjectDescription(),
                        m.getProjectStatus()))
                .collect(Collectors.toList());
    }
    public ProjectResponseDTO getProject(Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("프로젝트가 없습니다."));
        return new ProjectResponseDTO(project.getProjectId(),
                project.getProjectAdminId(),
                project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStatus());
    }
    public ProjectResponseDTO createProject(ProjectRequestDTO dto){

        Project project = new Project().builder()
                .projectAdminId(dto.getProjectAdminId())
                .projectDescription(dto.getProjectDescription())
                .projectName(dto.getProjectName())
                .projectStatus(ProjectStatus.ACTIVE.getStatus())
                .build();
        projectRepository.save(project);

        return new ProjectResponseDTO(project.getProjectId(),
                project.getProjectAdminId(),
                project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStatus());
    }
    public ResultDTO updateProject(Long id,ProjectRequestDTO dto){

        Project project = projectRepository.findById(id).orElseThrow(()->new NotFoundException("프로젝트가 없습니다."));

        project.updateStatus(ProjectStatus.valueOf(dto.getProjectStatus()).getStatus());
        projectRepository.save(project);
        return new ResultDTO("OK");
    }
}
