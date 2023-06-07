package com.nhnacademy.projectapi.tag.service;

import com.nhnacademy.projectapi.project.entity.Project;
import com.nhnacademy.projectapi.tag.entity.Tag;
import com.nhnacademy.projectapi.project.repository.ProjectRepository;
import com.nhnacademy.projectapi.tag.repository.TagRepository;
import com.nhnacademy.projectapi.tag.dto.TagRequestDTO;
import com.nhnacademy.projectapi.tag.dto.TagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<TagResponseDTO> getTags(Long projectId){
        List<Tag> list = tagRepository.findByProject_ProjectId(projectId).orElseThrow();
        return list.stream()
                .map(m -> new TagResponseDTO(m.getTagId(),
                        m.getTagContent()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public TagResponseDTO getTag(Long tagId){
        Tag tag = tagRepository.findById(tagId).orElseThrow();
        return new TagResponseDTO(tag.getTagId(),
                tag.getTagContent());
    }
    public void createTag(Long projectId, TagRequestDTO dto){
        Project project = projectRepository.findById(projectId).orElseThrow();
        Tag tag = new Tag().builder()
                .tagContent(dto.getTagContent())
                .project(project)
                .build();

        tagRepository.saveAndFlush(tag);
    }
    public void updateTag(Long tagId,TagRequestDTO dto){
        Tag tag = tagRepository.findById(tagId).orElseThrow();
        tag.modify(dto.getTagContent());
        tagRepository.save(tag);
    }
    public void deleteTag(Long tagId){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow();
        tagRepository.delete(tag);
    }
}
