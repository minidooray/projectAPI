package com.nhnacademy.projectapi.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.projectapi.task.dto.TaskRequestDTO;
import com.nhnacademy.projectapi.task.dto.TaskResponseDTO;
import com.nhnacademy.projectapi.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Test
    void getTasks() throws Exception {
        List<TaskResponseDTO> list = new ArrayList<>();
        list.add(new TaskResponseDTO(1L,"제목","내용","담당자","등록자",LocalDate.now(),LocalDate.now(),1L));
        given(taskService.getTasks(anyLong()))
                .willReturn(list);

        mockMvc.perform(get("/projects/{projectId}/tasks","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].taskTitle",equalTo("제목")));
    }

    @Test
    void getTask() throws Exception {
        TaskResponseDTO dto =new TaskResponseDTO(1L,"제목","내용","담당자","등록자",LocalDate.now(),LocalDate.now(),1L);

        given(taskService.getTask(anyLong()))
                .willReturn(dto);

        mockMvc.perform(get("/projects/{projectId}/tasks/{taskId}","1","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("taskTitle",equalTo("제목")));
    }

    @Test
    void createTask() throws Exception{
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTaskTitle("업무 제목");
        dto.setTaskContent("업무 내용");
        dto.setTaskManagerId("admin");
        dto.setTaskStartAt(LocalDate.now());
        dto.setTaskEndAt(LocalDate.now());
        TaskResponseDTO responseDTO =new TaskResponseDTO(1L,"업무 제목","업무 내용","admin","등록자",LocalDate.now(),LocalDate.now(),1L);
        given(taskService.createTask(anyLong(),any()))
                .willReturn(responseDTO);

        mockMvc.perform(post("/projects/{projectId}/tasks",1)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("taskTitle",equalTo("업무 제목")));
    }

    @Test
    void updateTask() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTaskManagerId("user8");
        dto.setTaskStartAt(LocalDate.now());
        dto.setTaskEndAt(LocalDate.now());

        TaskResponseDTO responseDTO =new TaskResponseDTO(1L,"업무 제목","업무 내용","admin","등록자",LocalDate.now(),LocalDate.now(),1L);
        given(taskService.createTask(anyLong(),any()))
                .willReturn(responseDTO);

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