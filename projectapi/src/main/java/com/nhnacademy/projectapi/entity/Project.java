package com.nhnacademy.projectapi.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "Projects")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @Column(name = "project_admin_id")
    private String projectAdminId;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_description")
    private String projectDescription;
    @Column(name = "project_status")
    private String projectStatus;

    public void updateStatus(String status){
        if(status != null){
            this.projectStatus = status;
        }
    }

}
