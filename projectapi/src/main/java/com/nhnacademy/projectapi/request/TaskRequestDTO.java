package com.nhnacademy.projectapi.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private String taskManagerId;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;




}
