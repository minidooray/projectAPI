package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.TaskTagRequestDTO;
import com.nhnacademy.projectapi.response.TaskTagResponseDTO;
import com.nhnacademy.projectapi.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/taskTags")
public class TaskTagController {
    private final TaskTagService taskTagService;

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskTagResponseDTO> getTaskTags(@PathVariable Long taskId){
        return taskTagService.getTaskTags(taskId);
    }
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public TaskTagResponseDTO getTaskTag(@PathVariable Long taskId){
//        return taskTagService.getTaskTag(taskId);
//    }

    @PostMapping(value = "/{taskId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createTaskTag(@PathVariable Long taskId,
                                @RequestBody TaskTagRequestDTO dto){
        taskTagService.createTaskTag(taskId,dto);
        return "{\"result\":\"OK\"}";
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTaskTag(@PathVariable Long tagId){
        taskTagService.deleteTaskTag(tagId);
        return "{\"result\":\"OK\"}";
    }

}
