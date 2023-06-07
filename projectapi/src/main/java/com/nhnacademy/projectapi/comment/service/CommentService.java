package com.nhnacademy.projectapi.comment.service;

import com.nhnacademy.projectapi.comment.entity.Comment;
import com.nhnacademy.projectapi.task.entity.Task;
import com.nhnacademy.projectapi.comment.repository.CommentRepository;
import com.nhnacademy.projectapi.task.repository.TaskRepository;
import com.nhnacademy.projectapi.comment.dto.CommentRequestDTO;
import com.nhnacademy.projectapi.comment.dto.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getComments(Long projectId){
        List<Comment> list = commentRepository.findByTask_TaskId(projectId).orElseThrow();
        return list.stream()
                .map(m -> new CommentResponseDTO(m.getCommentId(),
                        m.getCommentContent(),
                        m.getCommentAccountId(),
                        m.getCommentCreatedAt()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CommentResponseDTO getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        return new CommentResponseDTO(comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCommentAccountId(),
                comment.getCommentCreatedAt());
    }

    public void createComment(Long taskId, CommentRequestDTO dto){
        Task task = taskRepository.findById(taskId).orElseThrow();

        Comment comment = new Comment().builder()
                .commentContent(dto.getCommentContent())
                .commentAccountId(dto.getAccountId())
                .commentCreatedAt(LocalDate.now())
                .task(task)
                .build();

        commentRepository.saveAndFlush(comment);
    }

    public void updateComment(Long commentId,CommentRequestDTO dto){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.modify(dto.getCommentContent());
        commentRepository.save(comment);
    }
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        commentRepository.delete(comment);
    }
}
