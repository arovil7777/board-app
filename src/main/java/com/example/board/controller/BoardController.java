package com.example.board.controller;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.model.Board;
import com.example.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시글 목록 조회
     * 
     * @param model
     * @return list.jsp
     */
    @GetMapping("/list")
    public String list(Model model) {
        log.info("게시글 목록 조회");
        model.addAttribute("boards", boardService.getAllBoards());
        return "board/list";
    }

    /**
     * 게시글 상세 페이지
     * 
     * @param id
     * @param model
     * @return detail.jsp
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. ID: " + id)));
        return "board/detail";
    }

    /**
     * 게시글 작성 폼 페이지
     * 
     * @param model
     * @return form.jsp
     */
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    /**
     * 게시글 저장 처리
     * 
     * @param board
     * @return redirect:/board/list
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리
     * 
     * @param id
     * @return redirect:/board/list
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

}
