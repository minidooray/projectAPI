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
public class MilestoneRequestDTO {

    private String milestoneContent;
    private LocalDate milestoneStartAt;
    private LocalDate milestoneEndAt;
}
