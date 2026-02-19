package com;

import com.view.BoardView;

public class Main {
    public static void main(String[] args) throws InterruptedException{
       BoardView board = new BoardView(10000);
       board.printBoard();

       board.generateTransition();
    }
}