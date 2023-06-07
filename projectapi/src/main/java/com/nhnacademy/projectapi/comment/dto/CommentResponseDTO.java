package com.nhnacademy.projectapi.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String commentContent;
    private String accountId;
    private LocalDate commentCreatedAt;
}
