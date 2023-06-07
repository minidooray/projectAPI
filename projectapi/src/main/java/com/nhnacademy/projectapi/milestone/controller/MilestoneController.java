package com.nhnacademy.projectapi.milestone.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.milestone.dto.MilestoneRequestDTO;
import com.nhnacademy.projectapi.milestone.dto.MilestoneResponseDTO;
import com.nhnacademy.projectapi.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/milestones")
    @ResponseStatus(HttpStatus.OK)
    public List<MilestoneResponseDTO> getMilestones(@PathVariable Long projectId){
        return milestoneService.getMilestones(projectId);
    }

    @GetMapping("/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.OK)
    public MilestoneResponseDTO getMilestone(@PathVariable Long milestoneId){
        return milestoneService.getMilestone(milestoneId);
    }
    @PostMapping("/milestones")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createMilestone(@PathVariable Long projectId,
                                        @RequestBody MilestoneRequestDTO dto){
        milestoneService.createMilestone(projectId,dto);
        return new ResultDTO("OK");
    }
    @PatchMapping("/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO updateMilestone(@PathVariable Long milestoneId,
                                        @RequestBody MilestoneRequestDTO dto){
        milestoneService.updateMilestone(milestoneId,dto);
        return new ResultDTO("OK");
    }

    @DeleteMapping("/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO deleteMilestone(@PathVariable Long milestoneId){
        milestoneService.deleteMilestone(milestoneId);
        return new ResultDTO("OK");
    }
}
