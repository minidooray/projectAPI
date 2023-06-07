package com.nhnacademy.projectapi.comment.entity;

import com.nhnacademy.projectapi.task.entity.Task;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "Comments")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_account_id")
    private String commentAccountId;
    @Column(name="comment_created_at")
    private LocalDate commentCreatedAt;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public void modify(String commentContent){
        if(commentContent != null){
            this.commentContent = commentContent;
        }
    }

}
