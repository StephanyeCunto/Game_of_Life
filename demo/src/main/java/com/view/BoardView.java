package com.view;

import com.controller.BoardController;

public class BoardView {
    private BoardController controller;
    private int size;

    public BoardView(int size){
        this.size = size;
        this.controller = new BoardController(size);
    }

    public void printBoard(){
        System.out.println();
        for(int i=0; i<size+2;i++) System.out.println("=");

        for(int i=0; i<size; i++){
            System.out.print("| ");
            for(int j=0; j<size; j++){
                if(controller.getBoard().getCells()[i][j].isState()) System.out.print(" * ");
                else System.out.print(" - ");
            }
            
            System.out.println(" |");
        } 
        for(int i=0; i<size+2;i++) System.out.println("=");
    }

    public void generateTransition(){
        controller.generateTransition();
        printBoard();
    }
}
