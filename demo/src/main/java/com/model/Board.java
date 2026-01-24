package com.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[][] cells;
    private int size;

    public Board(int size){
        this.size = size;
        cells = new Cell[size][size];

        generateIntial();
    }

    private void generateIntial(){
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) {
            this.cells[i][j] = new Cell();
            this.cells[i][j].setPosition(new Position(i, j));
            this.cells[i][j].setState(ThreadLocalRandom.current().nextBoolean());
        }

        for(int i=0; i<size; i++) for(int j=0; j<size; j++) neighborAlives(cells[i][j]);
    }

    public void generateTransition(){
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) generateCellsTransition(cells[i][j]);
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) neighborAlives(cells[i][j]);
    }

    private void generateCellsTransition(Cell cell){
        int alives = cell.getAlives();

        if(cell.isState()){
            if(alives < 2 || alives > 3) cell.setState(false);
            else cell.setState(true);
        }else{
            if(alives == 3) cell.setState(true);
            else cell.setState(false);
        }
    }

    private void neighborAlives(Cell cell) {
        int alives = 0;
        List<Position> neighbors = neighbors(cell);

        for(Position p : neighbors) if(cells[p.getX()][p.getY()].isState()) alives++;
        cell.setAlives(alives);
    }

    private List<Position> neighbors(Cell cell){
        int x = cell.getPosition().getX();
        int y = cell.getPosition().getY();

        List<Position> neighbors = new ArrayList<>();

        if (x > 0) neighbors.add(new Position(x - 1, y));
        if (x < size - 1) neighbors.add(new Position(x + 1, y));
        
        if (y > 0) neighbors.add(new Position(x, y - 1));
        if (y < size - 1) neighbors.add(new Position(x, y + 1));

        if (x > 0 && y > 0) neighbors.add(new Position(x - 1, y - 1));
        if (x > 0 && y < size - 1) neighbors.add(new Position(x - 1, y + 1));
        if (x < size - 1 && y > 0) neighbors.add(new Position(x + 1, y - 1));
        if (x < size - 1 && y < size - 1) neighbors.add(new Position(x + 1, y + 1));

        return neighbors;
    }

    public void printBoard(){
        System.out.println();
        for(int i=0; i<size+2;i++) System.out.println("=");

        for(int i=0; i<size; i++){
            System.out.print("| ");
            for(int j=0; j<size; j++){
                if(cells[i][j].isState()) System.out.print(" * ");
                else System.out.print(" - ");
            }
            
            System.out.println(" |");
        } 
        for(int i=0; i<size+2;i++) System.out.println("=");
    }
}
