package com.nhnacademy.projectapi.tasktag.service;

import com.nhnacademy.projectapi.exception.NotFoundException;
import com.nhnacademy.projectapi.tag.entity.Tag;
import com.nhnacademy.projectapi.tag.repository.TagRepository;
import com.nhnacademy.projectapi.task.entity.Task;
import com.nhnacademy.projectapi.task.repository.TaskRepository;
import com.nhnacademy.projectapi.tasktag.dto.TaskTagResponseDTO;
import com.nhnacademy.projectapi.tasktag.entity.TaskTag;
import com.nhnacademy.projectapi.tasktag.repository.TaskTagRepository;
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
    public List<TaskTagResponseDTO> getTagByProject(Long taskId){
        List<TaskTag> list = taskTagRepository.findByPk_TaskId(taskId).orElseThrow();
        return list.stream()
                .map(m -> new TaskTagResponseDTO(m.getTag().getTagId()))
                .collect(Collectors.toList());
    }
    public TaskTagResponseDTO getTaskTag(Long tagId){
        TaskTag taskTag = taskTagRepository.findByPk_TagId(tagId).orElseThrow();
        return new TaskTagResponseDTO(taskTag.getPk().getTagId());
    }
    public void createTaskTag(Long taskId, Long tagId){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("태스크가 없습니다"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("태그가 없습니다."));
        TaskTag taskTag = new TaskTag().builder()
                .pk(new TaskTag.Pk(taskId, tagId))
                .tag(tag)
                .task(task)
                .build();
        taskTagRepository.save(taskTag);
    }

    public void deleteTaskTag(Long tagId){
        TaskTag taskTag = taskTagRepository.findByPk_TagId(tagId).orElseThrow();
        taskTagRepository.delete(taskTag);
    }
}
