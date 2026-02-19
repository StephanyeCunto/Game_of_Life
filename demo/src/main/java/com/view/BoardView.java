package com.view;

import com.controller.BoardController;

public class BoardView {
    private BoardController controller;

    public BoardView(int size) {
        this.controller = new BoardController(size);
    }

    public void generateTransition() throws InterruptedException{
        controller.generateTransition();
        printBoard();
    }

    public void printBoard() {
        int size = controller.getSize();
        boolean[][] snapshot = controller.getBoardSnapshot();

        System.out.println();
        for (int i = 0; i < size * 5; i++) System.out.print("=");
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++)
                System.out.print(snapshot[i][j] ? " * " : " - ");
            System.out.println(" |");
        }

        for (int i = 0; i < size * 5; i++) System.out.print("=");
    }
}