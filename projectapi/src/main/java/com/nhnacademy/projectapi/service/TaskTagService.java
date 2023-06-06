package com.nhnacademy.projectapi.service;

import com.nhnacademy.projectapi.entity.Tag;
import com.nhnacademy.projectapi.entity.Task;
import com.nhnacademy.projectapi.entity.TaskTag;
import com.nhnacademy.projectapi.repository.TagRepository;
import com.nhnacademy.projectapi.repository.TaskRepository;
import com.nhnacademy.projectapi.repository.TaskTagRepository;
import com.nhnacademy.projectapi.request.TaskTagRequestDTO;
import com.nhnacademy.projectapi.response.TaskTagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskTagService {
    private final TaskTagRepository taskTagRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    public List<TaskTagResponseDTO> getTaskTags(Long taskId){
        List<TaskTag> list = taskTagRepository.findByPk_TaskId(taskId).orElseThrow();
        return list.stream()
                .map(m -> new TaskTagResponseDTO(m.getTag().getTagId()))
                .collect(Collectors.toList());
    }
    public TaskTagResponseDTO getTaskTag(Long tagId){
        TaskTag taskTag = taskTagRepository.findByPk_TagId(tagId).orElseThrow();
        return new TaskTagResponseDTO(taskTag.getPk().getTagId());
    }
    public void createTaskTag(Long taskId, TaskTagRequestDTO dto){
        Task task = taskRepository.findById(taskId).orElseThrow();
        Tag tag = tagRepository.findById(dto.getTagId()).orElseThrow();
        TaskTag taskTag = new TaskTag().builder()
                .pk(new TaskTag.Pk(taskId, dto.getTagId()))
                .tag(tag)
                .task(task)
                .build();
        taskTagRepository.saveAndFlush(taskTag);
    }

    public void deleteTaskTag(Long tagId){
        TaskTag taskTag = taskTagRepository.findByPk_TagId(tagId).orElseThrow();
        taskTagRepository.delete(taskTag);
    }
}
