package com.nhnacademy.projectapi.tasktag.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.tasktag.dto.TaskTagResponseDTO;
import com.nhnacademy.projectapi.tasktag.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}")
public class TaskTagController {
    private final TaskTagService taskTagService;

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskTagResponseDTO> getTaskTags(@PathVariable Long taskId){
        return taskTagService.getTaskTags(taskId);
    }

    @PostMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createTaskTag(@PathVariable Long taskId,
                                   @PathVariable Long tagId){
        taskTagService.createTaskTag(taskId,tagId);
        return new ResultDTO("OK");
    }

    @DeleteMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO deleteTaskTag(@PathVariable Long tagId){
        taskTagService.deleteTaskTag(tagId);
        return new ResultDTO("OK");
    }
}
