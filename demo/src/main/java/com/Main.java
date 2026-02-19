package com;

import com.view.BoardView;

public class Main {
    public static void main(String[] args) {
       BoardView board = new BoardView(2);
       board.printBoard();

       board.generateTransition();
    }
}