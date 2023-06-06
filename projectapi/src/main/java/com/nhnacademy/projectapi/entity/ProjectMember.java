package com.nhnacademy.projectapi.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "Project_members")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {

    @EmbeddedId
    private Pk pk;
    @MapsId("projectId")
    @JoinColumn(name="project_id")
    @ManyToOne
    private Project project;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    @Getter
    public static class Pk implements Serializable {
        @Column(name = "account_id")
        private String accountId;
        @Column(name="project_id")
        private Long projectId;
    }
}
