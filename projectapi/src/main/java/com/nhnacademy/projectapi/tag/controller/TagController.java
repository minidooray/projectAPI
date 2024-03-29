package com.nhnacademy.projectapi.tag.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.tag.dto.TagRequestDTO;
import com.nhnacademy.projectapi.tag.dto.TagResponseDTO;
import com.nhnacademy.projectapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{projectId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponseDTO getTag(@PathVariable Long tagId){
        return tagService.getTag(tagId);
    }
    @PostMapping( "/{projectId}/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createTag(@PathVariable Long projectId,
                                     @RequestBody TagRequestDTO dto){
        tagService.createTag(projectId,dto);
        return new ResultDTO("OK");
    }

    @PatchMapping( "/{projectId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO updateTag(@PathVariable Long projectId,
                                     @PathVariable Long tagId,
                                     @RequestBody TagRequestDTO dto){
        tagService.updateTag(tagId,dto);
        return new ResultDTO("OK");
    }

    @DeleteMapping("/{projectId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO deleteTag(@PathVariable Long tagId){
        tagService.deleteTag(tagId);
        return new ResultDTO("OK");
    }
}
