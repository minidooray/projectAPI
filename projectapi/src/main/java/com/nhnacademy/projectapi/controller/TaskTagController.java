package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.response.TaskTagResponseDTO;
import com.nhnacademy.projectapi.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/taskTags")
public class TaskTagController {
    private final TaskTagService taskTagService;

    @GetMapping
    public List<TaskTagResponseDTO> getTaskTags(@PathVariable Long projectId){
        return taskTagService.getTaskTags(projectId);
    }
    @GetMapping("/{tagId}")
    public TaskTagResponseDTO getTaskTag(@PathVariable Long tagId){
        return taskTagService.getTaskTag(tagId);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String createTaskTag(@PathVariable Long projectId,
                                @PathVariable Long tagId){
        taskTagService.createTaskTag(projectId,tagId);
        return "{\"result\":\"OK\"}";
    }
}
