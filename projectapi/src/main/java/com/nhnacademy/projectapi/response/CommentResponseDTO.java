package com.nhnacademy.projectapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String commentContent;
    private String accountId;
    private Long taskId;

}
