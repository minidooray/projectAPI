package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.ProjectMemberRequestDTO;
import com.nhnacademy.projectapi.request.ProjectRequestDTO;
import com.nhnacademy.projectapi.response.ProjectMemberResponseDTO;
import com.nhnacademy.projectapi.response.ProjectResponseDTO;
import com.nhnacademy.projectapi.service.ProjectMemberService;
import com.nhnacademy.projectapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProjects(){
        return ResponseEntity.ok(projectService.getProjects());
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.getProject(projectId));
    }
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO dto){
        return ResponseEntity.ok(projectService.createProject(dto));
    }
    @PatchMapping("/{projectId}")
    public ResponseEntity updateProject(@PathVariable Long projectId,
                                                            @RequestBody ProjectRequestDTO dto){
        projectService.updateProject(projectId,dto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/{projectId}/members")
    public ResponseEntity registerProjectMember(@PathVariable Long projectId,
            @RequestBody ProjectMemberRequestDTO dto){
        projectMemberService.registerMember(projectId,dto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/{projectId}/members")
    public List<ProjectMemberResponseDTO> getProjectMember(@PathVariable Long projectId){
        return projectMemberService.getMembers(projectId);
    }
    @DeleteMapping("/{projectId}/members")
    public ResponseEntity deleteProjectMember(@PathVariable Long projectId,
                                              @RequestBody ProjectMemberRequestDTO dto){
        projectMemberService.deleteMember(projectId,dto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
