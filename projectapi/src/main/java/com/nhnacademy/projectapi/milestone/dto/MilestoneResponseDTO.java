package com.nhnacademy.projectapi.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneResponseDTO {
    private Long milestoneId;
    private String milestoneContent;
    private LocalDate milestoneStartAt;
    private LocalDate milestoneEndAt;
}
