package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.MilestoneRequestDTO;
import com.nhnacademy.projectapi.response.MilestoneResponseDTO;
import com.nhnacademy.projectapi.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/milestones", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createMilestone(@PathVariable Long projectId,
                                        @RequestBody MilestoneRequestDTO dto){
        milestoneService.createMilestone(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
    @PatchMapping(value = "/milestones/{milestoneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateMilestone(@PathVariable Long milestoneId,
                                        @RequestBody MilestoneRequestDTO dto){
        milestoneService.updateMilestone(milestoneId,dto);
        return "{\"result\":\"OK\"}";
    }

    @DeleteMapping(value = "/milestones/{milestoneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteMilestone(@PathVariable Long milestoneId){
        milestoneService.deleteMilestone(milestoneId);
        return "{\"result\":\"OK\"}";
    }
}
