package com;

import com.model.Board;

public class Main {
    public static void main(String[] args) {

        for(int i=0; i<10; i++){
            System.out.println(" Quadro novo: ");
            Board board = new Board(2);
            board.printBoard();
            board.generateTransition();
            board.generateTransition();
        }
    }
}