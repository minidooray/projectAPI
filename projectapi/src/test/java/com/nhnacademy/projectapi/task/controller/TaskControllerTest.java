package com.nhnacademy.projectapi.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.projectapi.task.dto.TaskRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Test
    void getTasks() throws Exception {
        mockMvc.perform(get("/projects/{projectId}/tasks","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].taskTitle",equalTo("업무 제목1")));
    }

    @Test
    void getTask() throws Exception {
        mockMvc.perform(get("/projects/{projectId}/tasks/{taskId}","1","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("taskTitle",equalTo("업무 제목1")));
    }

    @Test
    void createTask() throws Exception{
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTaskTitle("업무 제목");
        dto.setTaskContent("업무 내용");
        dto.setTaskManagerId("admin");
        dto.setTaskStartAt(LocalDate.now());
        dto.setTaskEndAt(LocalDate.now());
        mockMvc.perform(post("/projects/{projectId}/tasks",1)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }

    @Test
    void updateTask() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTaskManagerId("user8");
        dto.setTaskStartAt(LocalDate.now());
        dto.setTaskEndAt(LocalDate.now());
        mockMvc.perform(patch("/projects/{projectId}/tasks/4",1)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/projects/{projectId}/tasks/{taskId}","1","6"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result",equalTo("OK")));
    }
}