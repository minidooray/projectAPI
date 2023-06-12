package com.nhnacademy.projectapi.project.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.project.dto.ProjectRequestDTO;
import com.nhnacademy.projectapi.project.dto.ProjectResponseDTO;
import com.nhnacademy.projectapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponseDTO> getProjects(){
        return projectService.getProjects();
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponseDTO getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createProject(@RequestBody ProjectRequestDTO dto){
        projectService.createProject(dto);
        return new ResultDTO("OK");
    }

    @PatchMapping("/{projectId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO updateProject(@PathVariable Long projectId,
                                                            @RequestBody ProjectRequestDTO dto){
        projectService.updateProject(projectId,dto);
        return new ResultDTO("OK");
    }
}
