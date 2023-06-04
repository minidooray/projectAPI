package com.nhnacademy.projectapi.entity;

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
    private Long commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_account")
    private String commentAccount;
    @Column(name="comment_create_at")
    private LocalDate commentCreateAt;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
