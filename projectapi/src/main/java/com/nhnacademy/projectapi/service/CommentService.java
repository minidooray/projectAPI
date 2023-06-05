package com.nhnacademy.projectapi.service;

import com.nhnacademy.projectapi.entity.Comment;
import com.nhnacademy.projectapi.entity.Task;
import com.nhnacademy.projectapi.repository.CommentRepository;
import com.nhnacademy.projectapi.repository.TaskRepository;
import com.nhnacademy.projectapi.request.CommentRequestDTO;
import com.nhnacademy.projectapi.response.CommentResponseDTO;
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
                        m.getCommentAccount(),
                        m.getCommentCreateAt()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CommentResponseDTO getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        return new CommentResponseDTO(comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCommentAccount(),
                comment.getCommentCreateAt());
    }

    public void createComment(Long taskId, CommentRequestDTO dto){
        Task task = taskRepository.findById(taskId).orElseThrow();

        Comment comment = new Comment().builder()
                .commentContent(dto.getCommentContent())
                .commentAccount(dto.getAccountId())
                .commentCreateAt(LocalDate.now())
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
