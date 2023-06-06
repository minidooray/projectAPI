package com.nhnacademy.projectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "Milestones")
@Getter
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
    @JoinColumn(name = "project_id")
    private Project project;

    public void modify(String milestoneContent, LocalDate milestoneStartAt, LocalDate milestoneEndAt){
        if(milestoneContent != null){
            this.milestoneContent = milestoneContent;
        }
        if(milestoneStartAt != null){
            this.milestoneStartAt = milestoneStartAt;
        }
        if(milestoneEndAt != null){
            this.milestoneEndAt = milestoneEndAt;
        }
    }
}
