package com.nhnacademy.projectapi.task.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.task.dto.TaskRequestDTO;
import com.nhnacademy.projectapi.task.dto.TaskResponseDTO;
import com.nhnacademy.projectapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return taskService.getTasks(projectId);
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO getTask(@PathVariable Long projectId,
                                   @PathVariable Long taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping("/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createTask(@PathVariable Long projectId,
                                @RequestBody TaskRequestDTO dto){
        taskService.createTask(projectId,dto);
        return new ResultDTO("OK");
    }
    @PatchMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO updateTask(@PathVariable Long projectId,
                                @PathVariable Long taskId,
                                @RequestBody TaskRequestDTO dto){
        taskService.updateTask(projectId,taskId,dto);
        return new ResultDTO("OK");
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResultDTO("OK");
    }
}
