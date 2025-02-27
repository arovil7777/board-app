package com.example.board.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
