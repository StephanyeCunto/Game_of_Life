package com.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;

@Getter
public class Board {
    private Cell[][] cells;
    private int size;

    public Board(int size){
        this.size = size;
        cells = new Cell[size][size];
        fillCell(cells);
    }

    private void fillCell(Cell[][] cell){
       for(int i=0; i<size; i++) for(int j=0; j<size; j++) cell[i][j] = new Cell(false);
    }

    public void generateIntial(){
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) this.cells[i][j].setState(ThreadLocalRandom.current().nextBoolean());
    }

    public void generateTransition(){
        Cell[][] cellsNext = new Cell[size][size];
        fillCell(cellsNext);

        for(int i=0; i<size; i++) for(int j=0; j<size; j++) generateCellsTransition(i, j, cellsNext);

        for(int i=0; i<size; i++) for(int j=0; j<size; j++) this.cells[i][j] = cellsNext[i][j];
    }

    private void generateCellsTransition(int i, int j, Cell[][] cellsNext){
        int alives = neighborAlives(i, j);
        boolean state = false;

        state = this.cells[i][j].isState() ? (alives >= 2 && alives <= 3) : (alives == 3);

        cellsNext[i][j].setState(state);
    }

    private int neighborAlives(int i, int j) {
        int alives = 0;
        List<Position> neighbors = neighbors(i,j);

        for(Position p : neighbors) if(cells[p.getX()][p.getY()].isState()) alives++;

        return alives;
    }

    private List<Position> neighbors(int x, int y){
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
}