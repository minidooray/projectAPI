package com.nhnacademy.projectapi.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "Project_members")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {

    @EmbeddedId
    Pk pk;

    @MapsId("projectId")
    @JoinColumn(name="projectId")
    @ManyToOne
    private Project project;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    private static class Pk implements Serializable {
        @Column(name = "account_id")
        private String accountId;
        @Column(name="project_id")
        private Long projectId;
    }
}
