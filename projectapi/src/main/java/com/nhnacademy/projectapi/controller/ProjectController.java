package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.ProjectMemberRequestDTO;
import com.nhnacademy.projectapi.request.ProjectRequestDTO;
import com.nhnacademy.projectapi.response.ProjectMemberResponseDTO;
import com.nhnacademy.projectapi.response.ProjectResponseDTO;
import com.nhnacademy.projectapi.service.ProjectMemberService;
import com.nhnacademy.projectapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public List<ProjectResponseDTO> getProjects(){
        return projectService.getProjects();
    }
    @GetMapping("/{projectId}")
    public ProjectResponseDTO getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProject(@RequestBody ProjectRequestDTO dto){
        projectService.createProject(dto);
        return "{\"result\":\"OK\"}";
    }
    @PatchMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProject(@PathVariable Long projectId,
                                                            @RequestBody ProjectRequestDTO dto){
        projectService.updateProject(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
    @PostMapping(value = "/{projectId}/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerProjectMember(@PathVariable Long projectId,
            @RequestBody ProjectMemberRequestDTO dto){
        projectMemberService.registerMember(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
    @PostMapping("/{projectId}/members")
    public List<ProjectMemberResponseDTO> getProjectMember(@PathVariable Long projectId){
        return projectMemberService.getMembers(projectId);
    }
    @DeleteMapping(value = "/{projectId}/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProjectMember(@PathVariable Long projectId,
                                              @RequestBody ProjectMemberRequestDTO dto){
        projectMemberService.deleteMember(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
}
