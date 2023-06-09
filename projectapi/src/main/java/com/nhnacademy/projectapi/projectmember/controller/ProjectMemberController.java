package com.nhnacademy.projectapi.projectmember.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.projectmember.dto.ProjectMemberAccountDTO;
import com.nhnacademy.projectapi.projectmember.dto.ProjectMemberProjectDTO;
import com.nhnacademy.projectapi.projectmember.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping(value = "/{projectId}/members/{loginId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO registerProjectMember(@PathVariable Long projectId,
            @PathVariable String loginId){
        projectMemberService.registerMember(projectId,loginId);
        return new ResultDTO("OK");
    }

    @GetMapping("/{projectId}/members")
    public List<ProjectMemberAccountDTO> getProjectMembers(@PathVariable Long projectId){
        return projectMemberService.getMembers(projectId);
    }

    @GetMapping("/members/{loginId}")
    public List<ProjectMemberProjectDTO> getProjectByMember(@PathVariable String loginId){
        return projectMemberService.getMemberByProject(loginId);
    }
    @DeleteMapping(value = "/{projectId}/members/{loginId}")
    public ResultDTO deleteProjectMember(@PathVariable Long projectId,
                                      @PathVariable String loginId){
        projectMemberService.deleteMember(projectId,loginId);
        return new ResultDTO("OK");
    }
}
