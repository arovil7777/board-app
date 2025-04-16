package com.example.board.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.model.Board;
import com.example.board.service.BoardService;

@RestController
@RequestMapping("/api/boards")
public class RestPostController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public Page<Board> getBoards(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            return boardService.searchBoards(keyword, pageable);
        }
        return boardService.getAllBoards(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Board> getBoard(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

}
