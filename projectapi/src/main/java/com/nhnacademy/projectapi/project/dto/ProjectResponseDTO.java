package com.nhnacademy.projectapi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {
    private Long projectId;
    private String projectAdminId;
    private String projectName;
    private String projectDescription;
    private String projectStatus;
}
