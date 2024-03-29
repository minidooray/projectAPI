package com.nhnacademy.projectapi.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.project.dto.ProjectRequestDTO;
import com.nhnacademy.projectapi.project.dto.ProjectResponseDTO;
import com.nhnacademy.projectapi.project.service.ProjectService;
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

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ProjectService projectService;

    @Test
    void getProjects() throws Exception{
        List<ProjectResponseDTO> list = new ArrayList<>();
        list.add(new ProjectResponseDTO(1L,"admin","프로젝트1","설명","ACTIVE"));
        given(projectService.getProjects())
                .willReturn(list);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectAdminId",equalTo("admin")));
    }

    @Test
    void getProject() throws Exception {
        ProjectResponseDTO dto = new ProjectResponseDTO(1L,"admin","프로젝트1","설명","ACTIVE");
        given(projectService.getProject(anyLong()))
                .willReturn(dto);

        mockMvc.perform(get("/projects/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("projectId",equalTo(1)));
    }

    @Test
    void createProject() throws Exception{
        ProjectResponseDTO dto = new ProjectResponseDTO(1L,"admin","프로젝트1","설명","ACTIVE");

        given(projectService.createProject(any()))
                .willReturn(dto);

        mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("projectId",equalTo(1)));
    }

    @Test
    void updateProject() throws Exception{
        ProjectRequestDTO dto = new ProjectRequestDTO();
        dto.setProjectStatus("SLEEP");

        ResultDTO response = new ResultDTO("OK");

        given(projectService.updateProject(anyLong(),any()))
                .willReturn(response);

        mockMvc.perform(patch("/projects/1")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
}