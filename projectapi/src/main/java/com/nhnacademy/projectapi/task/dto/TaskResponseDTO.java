package com.nhnacademy.projectapi.task.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {

    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private String taskManagerId;
    private String taskRegisterId;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private Long milestoneId;
}
