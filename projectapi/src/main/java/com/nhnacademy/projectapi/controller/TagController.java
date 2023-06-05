package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.TagRequestDTO;
import com.nhnacademy.projectapi.response.TagResponseDTO;
import com.nhnacademy.projectapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class TagController {

    private final TagService tagService;

    @GetMapping("/{projectId}/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponseDTO> getTags(@PathVariable Long projectId){
        return tagService.getTags(projectId);
    }

    @GetMapping("/{projectId}/tags/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponseDTO getTag(@PathVariable Long taskId){
        return tagService.getTag(taskId);
    }

    @PostMapping(value = "/{projectId}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createTag(@PathVariable Long projectId,
                                     @RequestBody TagRequestDTO dto){
        tagService.createTag(projectId,dto);
        return "{\"result\":\"OK\"}";
    }

    @PatchMapping(value = "/{projectId}/tags/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateTag(@PathVariable Long projectId,
                                     @PathVariable Long taskId,
                                     @RequestBody TagRequestDTO dto){
        tagService.updateTag(taskId,dto);
        return "{\"result\":\"OK\"}";
    }

    @DeleteMapping(value="/{projectId}/milestones/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTag(@PathVariable Long taskId){
        tagService.deleteTag(taskId);
        return "{\"result\":\"OK\"}";
    }
}
