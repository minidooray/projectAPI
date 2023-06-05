package com.nhnacademy.projectapi.controller;

import com.nhnacademy.projectapi.request.CommentRequestDTO;
import com.nhnacademy.projectapi.response.CommentResponseDTO;
import com.nhnacademy.projectapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDTO> getComments(@PathVariable Long projectId){
        return commentService.getComments(projectId);
    }
    @GetMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDTO getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }
    @PostMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String createComment(@PathVariable Long projectId,
                                  @RequestBody CommentRequestDTO dto){
        commentService.createComment(projectId,dto);
        return "{\"result\":\"OK\"}";
    }
    @PatchMapping(value = "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateComment(@PathVariable Long commentId,
                                  @RequestBody CommentRequestDTO dto){
        commentService.updateComment(commentId,dto);
        return "{\"result\":\"OK\"}";
    }
    @DeleteMapping(value = "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "{\"result\":\"OK\"}";
    }

}
