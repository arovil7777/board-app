package com.example.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.model.Board;
import com.example.board.model.User;
import com.example.board.service.BoardService;
import com.example.board.service.UserService;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    // 게시글 목록 보기
    @GetMapping
    public String listBoards(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Board> boardPage;
        if (keyword != null && !keyword.isEmpty()) {
            boardPage = boardService.searchBoards(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            boardPage = boardService.getAllBoards(pageable);
        }
        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("totalPages", boardPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "board/list";
    }

    // 새 게시글 작성 폼
    @GetMapping("/write")
    public String newBoard(Model model) {
        model.addAttribute("board", new Board());
        return "board/write";
    }

    // 게시글 저장 처리
    @PostMapping("/save")
    public String saveBoard(@ModelAttribute Board board, Authentication authentication) {
        // 현재 로그인한 사용자 정보를 가져와 게시글 작성자 설정
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        board.setUser(user);
        boardService.saveBoard(board);
        return "redirect:/boards";
    }

    // 게시글 상세보기
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        var board = boardService.getBoardById(id);
        if (board.isPresent()) {
            model.addAttribute("board", board.get());
            return "board/detail";
        } else {
            return "redirect:/boards";
        }
    }

    // 게시글 수정 양식 (수정 시 기존 데이터 로드)
    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        Optional<Board> board = boardService.getBoardById(id);
        if (board.isPresent()) {
            model.addAttribute("board", board.get());
            return "board/write";
        } else {
            return "redirect:/boards";
        }
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}
