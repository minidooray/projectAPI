package com.nhnacademy.projectapi.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "Projects")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @JoinColumn(name = "project_admin_id")
    private String projectAdminId;
    @JoinColumn(name = "project_name")
    private String projectName;
    @JoinColumn(name = "project_description")
    private String projectDescription;
    @JoinColumn(name = "project_status")
    private String projectStatus;


}
