package com.nhnacademy.projectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "Milestones")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long milestoneId;
    @Column(name = "milestone_Content")
    private String milestoneContent;
    @Column(name = "milestone_start_at")
    private LocalDate milestoneStartAt;
    @Column(name = "milestone_end_at")
    private LocalDate milestoneEndAt;
    @ManyToOne
    @Column(name = "project_id")
    private Project project;

}
