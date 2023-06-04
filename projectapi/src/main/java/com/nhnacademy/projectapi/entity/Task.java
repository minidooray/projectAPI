package com.nhnacademy.projectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "Tasks")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "task_content")
    private String taskContent;
    @Column(name = "task_manager_id")
    private String taskManagerId;
    @OneToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;
    @Column(name = "task_start_at")
    private LocalDate taskStartAt;
    @Column(name = "task_end_at")
    private LocalDate taskEndAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_no")
    private Project project;


}
