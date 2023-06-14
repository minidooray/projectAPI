package com.nhnacademy.projectapi.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        mockMvc.perform(get("/projects/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("projectId",equalTo(2)));
    }

    @Test
    void createProject() throws Exception{
        ProjectRequestDTO dto = new ProjectRequestDTO("user7","project7","7번 프로젝트","ACTIVE");
        mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }

    @Test
    void updateProject() throws Exception{
        ProjectRequestDTO dto = new ProjectRequestDTO();
        dto.setProjectStatus("SLEEP");
        mockMvc.perform(patch("/projects/6")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));

        mockMvc.perform(get("/projects/6"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("projectStatus",equalTo("SLEEP")));
    }
}