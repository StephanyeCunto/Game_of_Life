package com.model;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[] cells;
    private int size;

    public Board(int size){
        this.size = size;
        cells= new Cell[size*size];
        
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) generateIntial(i, j);
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) neighborAlive(i * size + j);
    }

    private void generateIntial(int x, int y){
        int index = x * size + y;
        this.cells[index] = new Cell();
        this.cells[index].setPosition(new Position(x,y));

        boolean valor = ThreadLocalRandom.current().nextBoolean();
        this.cells[index].setState(valor);
    }

    private void generateCellsTransition(int index){
        int alives = cells[index].getAlives();

        if(cells[index].isState()){
            if(alives < 2 || alives > 3) this.cells[index].setState(false);
            else this.cells[index].setState(true);
        } else{
            if(alives == 3) this.cells[index].setState(true);
            else this.cells[index].setState(false);
        }
    }

    private void neighborAlive(int index) {
        int alives = 0;
        Position[] neighbors = neighbors(index);

        for (Cell cell : cells) {
            if (!cell.isState()) continue;

            for (Position p : neighbors) {
                if (cell.getPosition().equals(p)) {
                    alives++;
                    break;
                }
            }
        }

        cells[index].setAlives(alives);
    }

    private Position[] neighbors(int index){
        int x = cells[index].getPosition().getX();
        int y = cells[index].getPosition().getY();

        Position[] neighbors = {
            new Position(x - 1, y), new Position(x + 1, y), new Position(x, y - 1), new Position(x, y + 1),
            new Position(x - 1, y + 1), new Position(x + 1, y + 1), new Position(x - 1, y - 1), new Position(x + 1, y - 1)
        };

        return neighbors;
    }

    public void generateTransition(){
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) generateCellsTransition(i * size + j);

        for(int i=0; i<size; i++) for(int j=0; j<size; j++) neighborAlive(i * size + j);
    }

    public void printBoard(){
        int x = 0;

        System.out.println();
        System.out.println("============================");
        System.out.print("| ");
        for(Cell cell : this.cells){
            if(x != cell.getPosition().getX()) {
                System.out.println(" |");
                System.out.print("| ");
                x++;
            }

            if( cell.isState()) System.out.print(" * ");
            else System.out.print(" _ ");
        }

        System.out.println(" |");
        System.out.println("============================");
    }
}
