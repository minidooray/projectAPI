package com.nhnacademy.projectapi.tasktag.entity;

import com.nhnacademy.projectapi.tag.entity.Tag;
import com.nhnacademy.projectapi.task.entity.Task;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "TaskTags")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TaskTag {

    @EmbeddedId
    private Pk pk;

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
    public static class Pk implements Serializable {
        @Column(name = "task_id")
        private Long taskId;
        @Column(name="tag_id")
        private Long tagId;
    }
}
