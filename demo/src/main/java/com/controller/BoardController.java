package com.controller;

import com.model.Board;

import lombok.Getter;

@Getter
public class BoardController {
    private int size;
    private Board board;

    public BoardController(int size){
        this.size = size;
        this.board = new Board(size);

        generateIntial();
    }

    public void generateIntial(){
        board.generateIntial();
    }

    public void generateTransition(){
        this.board.generateTransition();
    }
}
