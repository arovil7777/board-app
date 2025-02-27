package com.example.board.service;

import java.util.List;
import java.util.Optional;

import com.example.board.model.Board;

public interface BoardService {
    List<Board> getAllBoards();

    Optional<Board> getBoardById(Long id);

    void saveBoard(Board board);

    void deleteBoard(Long id);
}
