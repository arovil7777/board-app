package com.example.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.model.Board;
import com.example.board.model.Comment;
import com.example.board.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByBoard(Board board) {
        return commentRepository.findByBoard(board);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}
