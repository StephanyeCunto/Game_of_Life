package com.view;

import com.controller.BoardController;

public class BoardView {
    private BoardController controller;
    private int size;

    public BoardView(int size){
        this.size = size;
        this.controller = new BoardController(size);
    }

    public void generateTransition(){
        controller.generateTransition();
        printBoard();
    }

    public void printBoard(){
        System.out.println();
        for(int i=0; i<size*5;i++) System.out.print("=");
        System.out.println();

        for(int i=0; i<size; i++){
            System.out.print("| ");
            for(int j=0; j<size; j++){
                if(controller.getCellState(i, j)) System.out.print(" * ");
                else System.out.print(" - ");
            }
            
            System.out.println(" |");
        } 
        for(int i=0; i<size*5;i++) System.out.print("=");
    }

}
