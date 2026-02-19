package com.controller;

import com.model.Board;
import com.model.Cell;
import com.service.GameService;

public class BoardController {
    private int size;
    private Board board;
    private GameService service;

    public BoardController(int size) {
        this.size = size;
        this.board = new Board(size);
        this.service = new GameService(size);
        this.board.setCells(this.service.generateIntial());
    }

    public void generateTransition() {
        this.board.setCells(this.service.generateTransition(this.board.getCells()));
    }

    public boolean[][] getBoardSnapshot() {
        Cell[][] cells = board.getCells();
        boolean[][] snapshot = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                snapshot[i][j] = cells[i][j].isState();
        return snapshot;
    }

    public int getSize() { return size; }
}
