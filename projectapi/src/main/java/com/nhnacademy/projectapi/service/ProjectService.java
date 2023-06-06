package com.nhnacademy.projectapi.service;

import com.nhnacademy.projectapi.entity.Project;
import com.nhnacademy.projectapi.repository.ProjectRepository;
import com.nhnacademy.projectapi.request.ProjectRequestDTO;
import com.nhnacademy.projectapi.response.ProjectResponseDTO;
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
        Project project = projectRepository.findById(id).orElseThrow();
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
                .projectStatus("ACTIVE")
                .build();
        projectRepository.saveAndFlush(project);

        return new ProjectResponseDTO(project.getProjectId(),
                project.getProjectAdminId(),
                project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStatus());
    }
    public void updateProject(Long id,ProjectRequestDTO dto){

        Project project = projectRepository.findById(id).orElseThrow();

        project.updateStatus(dto.getProjectStatus());

        projectRepository.save(project);
    }
}
