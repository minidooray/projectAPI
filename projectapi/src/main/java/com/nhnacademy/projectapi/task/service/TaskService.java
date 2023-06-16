package com.nhnacademy.projectapi.task.service;

import com.nhnacademy.projectapi.exception.NotFoundException;
import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.project.repository.ProjectRepository;
import com.nhnacademy.projectapi.task.dto.TaskRequestDTO;
import com.nhnacademy.projectapi.task.dto.TaskResponseDTO;
import com.nhnacademy.projectapi.task.entity.Task;
import com.nhnacademy.projectapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public List<TaskResponseDTO> getTasks(Long projectId){
        List<Task> list = taskRepository.findByProject_ProjectId(projectId).orElseThrow();
        return list.stream()
                .map(m -> new TaskResponseDTO(m.getTaskId(),
                        m.getTaskTitle(),
                        m.getTaskContent(),
                        m.getTaskManagerId(),
                        m.getTaskRegisterId(),
                        m.getTaskStartAt(),
                        m.getTaskEndAt(),
                        m.getMilestoneId()))
                .collect(Collectors.toList());
    }
    public TaskResponseDTO getTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()->new NotFoundException("태스크가 없습니다."));
        return new TaskResponseDTO(task.getTaskId(),
                task.getTaskTitle(),
                task.getTaskContent(),
                task.getTaskManagerId(),
                task.getTaskRegisterId(),
                task.getTaskStartAt(),
                task.getTaskEndAt(),
                task.getMilestoneId());
    }
    public TaskResponseDTO createTask(Long id,TaskRequestDTO dto){
        Project project = projectRepository.findById(id).orElseThrow(()->new NotFoundException("프로젝트가 없습니다."));
        Task task = new Task().builder()
                .taskTitle(dto.getTaskTitle())
                .taskManagerId(dto.getTaskManagerId())
                .taskRegisterId(dto.getTaskRegisterId())
                .taskStartAt(dto.getTaskStartAt())
                .taskEndAt(dto.getTaskEndAt())
                .milestoneId(dto.getMilestoneId())
                .taskContent(dto.getTaskContent())
                .project(project)
                .build();
        taskRepository.save(task);
        return new TaskResponseDTO(task.getTaskId(),
                task.getTaskTitle(),
                task.getTaskContent(),
                task.getTaskManagerId(),
                task.getTaskRegisterId(),
                task.getTaskStartAt(),
                task.getTaskEndAt(),
                task.getMilestoneId());
    }

    public void updateTask(Long projectId,Long taskId,TaskRequestDTO dto){
        Task task = taskRepository.findById(taskId).orElseThrow(()->new NotFoundException("태스크가 없습니다."));
        task.modify(dto.getTaskTitle(), dto.getTaskContent(), dto.getTaskManagerId(),
                dto.getMilestoneId(),dto.getTaskStartAt(),dto.getTaskEndAt());
        taskRepository.save(task);

    }
    public void deleteTask(Long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new NotFoundException("태스크가 없습니다."));
        taskRepository.delete(task);
    }
}
