package com.nhnacademy.projectapi.comment.controller;

import com.nhnacademy.projectapi.ResultDTO;
import com.nhnacademy.projectapi.comment.dto.CommentRequestDTO;
import com.nhnacademy.projectapi.comment.dto.CommentResponseDTO;
import com.nhnacademy.projectapi.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDTO> getComments(@PathVariable Long taskId){
        return commentService.getComments(taskId);
    }
    @GetMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDTO getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO createComment(@PathVariable Long taskId,
                                  @RequestBody CommentRequestDTO dto){
        commentService.createComment(taskId,dto);
        return new ResultDTO("OK");
    }
    @PatchMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO updateComment(@PathVariable Long commentId,
                                  @RequestBody CommentRequestDTO dto){
        commentService.updateComment(commentId,dto);
        return new ResultDTO("OK");
    }
    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultDTO deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResultDTO("OK");
    }
}
