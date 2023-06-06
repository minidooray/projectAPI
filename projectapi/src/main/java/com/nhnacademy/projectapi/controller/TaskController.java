package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.TaskRequestDTO;
import com.nhnacademy.projectapi.response.TaskResponseDTO;
import com.nhnacademy.projectapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{projectId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDTO> getTasks(@PathVariable Long projectId){
        return taskService.getTasks();
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO getTask(@PathVariable Long projectId,
                                                      @PathVariable Long taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping(value = "/{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@PathVariable Long projectId,
                                        @RequestBody TaskRequestDTO dto){
        taskService.createTask(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
    @PatchMapping(value = "/{projectId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateTask(@PathVariable Long projectId,
                                        @PathVariable Long taskId,
                                        @RequestBody TaskRequestDTO dto){
        taskService.updateTask(projectId,taskId,dto);
        return "{\"result\":\"OK\"}";
    }

    @DeleteMapping(value = "/{projectId}/milestones/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return "{\"result\":\"OK\"}";
    }




}
