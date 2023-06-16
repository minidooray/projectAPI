package com.nhnacademy.projectapi.tag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.tag.dto.TagRequestDTO;
import com.nhnacademy.projectapi.tag.dto.TagResponseDTO;
import com.nhnacademy.projectapi.tag.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
@AutoConfigureMockMvc
class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagService tagService;
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void getTags() throws Exception {
        List<TagResponseDTO> list = new ArrayList<>();
        list.add(new TagResponseDTO(1L,"태그내용"));
        given(tagService.getTags(anyLong()))
                .willReturn(list);

        mockMvc.perform(get("/projects/1/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagContent",equalTo("태그내용")));
    }
    @Test
    void createTag() throws Exception {
        TagRequestDTO dto = new TagRequestDTO("태그내용");
        ResultDTO response = new ResultDTO("OK");
        given(tagService.createTag(anyLong(),any()))
                .willReturn(response);

        mockMvc.perform(post("/projects/2/tags")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
    @Test
    void updateTag() throws Exception {
        TagRequestDTO dto = new TagRequestDTO("태그내용");
        ResultDTO response = new ResultDTO("OK");
        given(tagService.updateTag(any(),any()))
                .willReturn(response);

        mockMvc.perform(patch("/projects/1/tags/1")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
    @Test
    void deleteTag() throws Exception {
        TagResponseDTO dto = new TagResponseDTO(1L,"태그내용");
        ResultDTO response = new ResultDTO("OK");

        given(tagService.deleteTag(anyLong()))
                .willReturn(response);

        mockMvc.perform(delete("/projects/1/tags/1")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
}