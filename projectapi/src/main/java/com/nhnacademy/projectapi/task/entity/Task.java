package com.nhnacademy.projectapi.task.entity;

import com.nhnacademy.projectapi.project.entity.Project;
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
    @Column(name = "task_register_id")
    private String taskRegisterId;
    @Column(name = "milestone_id")
    private Long milestoneId;
    @Column(name = "task_start_at")
    private LocalDate taskStartAt;
    @Column(name = "task_end_at")
    private LocalDate taskEndAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public void modify(String taskTitle,String taskContent,String taskManagerId,
                       Long milestoneId, LocalDate taskStartAt, LocalDate taskEndAt){
        if(taskTitle != null){
            this.taskTitle = taskTitle;
        }
        if(taskContent != null){
            this.taskContent = taskContent;
        }
        if(taskManagerId != null){
            this.taskManagerId = taskManagerId;
        }
        if(milestoneId != null){
            this.milestoneId = milestoneId;
        }
        if(taskStartAt != null){
            this.taskStartAt = taskStartAt;
        }
        if(taskEndAt != null){
            this.taskEndAt = taskEndAt;
        }
    }
}
