package com.example.board.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt = LocalDateTime.now();

}
