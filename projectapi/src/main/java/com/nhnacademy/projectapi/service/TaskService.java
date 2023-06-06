package com.nhnacademy.projectapi.service;

import com.nhnacademy.projectapi.entity.Milestone;
import com.nhnacademy.projectapi.entity.Project;
import com.nhnacademy.projectapi.entity.Task;
import com.nhnacademy.projectapi.repository.MilestoneRepository;
import com.nhnacademy.projectapi.repository.ProjectRepository;
import com.nhnacademy.projectapi.repository.TaskRepository;
import com.nhnacademy.projectapi.request.TaskRequestDTO;
import com.nhnacademy.projectapi.response.TaskResponseDTO;
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
    private final MilestoneRepository milestoneRepository;

    public List<TaskResponseDTO> getTasks(){
        List<Task> list = taskRepository.findAll();
        return list.stream()
                .map(m -> new TaskResponseDTO(m.getTaskId(),
                        m.getTaskTitle(),
                        m.getTaskContent(),
                        m.getTaskManagerId(),
                        m.getTaskStartAt(),
                        m.getTaskEndAt()))
                .collect(Collectors.toList());
    }
    public TaskResponseDTO getTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow();
        return new TaskResponseDTO(task.getTaskId(),
                task.getTaskTitle(),
                task.getTaskContent(),
                task.getTaskManagerId(),
                task.getTaskStartAt(),
                task.getTaskEndAt());
    }
    public void createTask(Long id,TaskRequestDTO dto){
        Project project = projectRepository.findById(id).orElseThrow();

        Task task = new Task().builder()
                .taskTitle(dto.getTaskTitle())
                .taskManagerId(dto.getTaskManagerId())
                .taskStartAt(dto.getTaskStartAt())
                .taskEndAt(dto.getTaskEndAt())
                .taskContent(dto.getTaskContent())
                .project(project)
                .build();

        taskRepository.saveAndFlush(task);
    }

    public void updateTask(Long projectId,Long taskId,TaskRequestDTO dto){
        Task task = taskRepository.findById(taskId).orElseThrow();
        Milestone milestone = milestoneRepository.findById(dto.getMilestoneId()).orElseThrow();
        task.modify(dto.getTaskTitle(), dto.getTaskContent(), dto.getTaskManagerId(),
                milestone,dto.getTaskStartAt(),dto.getTaskEndAt());
        taskRepository.save(task);
    }
    public void deleteTask(Long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow();
        taskRepository.delete(task);
    }



}
