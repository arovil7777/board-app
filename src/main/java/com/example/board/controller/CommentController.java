package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.model.Board;
import com.example.board.model.Comment;
import com.example.board.model.User;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.UserService;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    // 댓글 작성 처리
    @PostMapping("/write")
    public String saveComment(@RequestParam("boardId") Long boardId, @RequestParam("content") String content,
            Authentication authentication) {
        var optionalBoard = boardService.getBoardById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setBoard(board);
            comment.setUser(user);
            commentService.saveComment(comment);
        }
        return "redirect:/boards/" + boardId;
    }
}
