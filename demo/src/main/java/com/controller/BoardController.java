package com.controller;

import com.model.Board;
import com.service.GameService;

import lombok.Getter;

@Getter
public class BoardController {
    private int size;
    private Board board;
    private GameService service;

    public BoardController(int size){
        this.size = size;
        this.board = new Board(size);
        this.service = new GameService(size);

        this.board.setCells(this.service.generateIntial());
    }

    public void generateIntial(){
       this.board.setCells(this.service.generateIntial());
    }

    public void generateTransition(){
        this.board.setCells(this.service.generateTransition(this.board.getCells()));
    }
}
