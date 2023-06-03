package com.nhnacademy.projectapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "TaskTags")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TaskTag {

    @EmbeddedId
    Pk pk;

    @MapsId("taskId")
    @JoinColumn(name="task_id")
    @ManyToOne
    private Task task;
    @MapsId("tagId")
    @JoinColumn(name="tag_id")
    @ManyToOne
    private Tag tag;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    private static class Pk implements Serializable {
        @Column(name = "task_id")
        private Long taskId;
        @Column(name="tag_id")
        private Long tagId;
    }
}
