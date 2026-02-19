package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.model.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameService {
    private int size;

    public Cell[][] generateIntial(){
        Cell[][] cells = new Cell[size][size];
        for(int i=0; i<size; i++) for(int j=0; j<size; j++) cells[i][j] = new Cell(ThreadLocalRandom.current().nextBoolean());

        return cells;
    }

    private void fillCell(Cell[][] cell){
       for(int i=0; i<size; i++) for(int j=0; j<size; j++) cell[i][j] = new Cell(false);
    }

    public Cell[][] generateTransition(Cell[][] cells){
        Cell[][] cellsNext = new Cell[size][size];
        fillCell(cellsNext);

        for(int i=0; i<size; i++) for(int j=0; j<size; j++) generateCellsTransition(i, j, cells,cellsNext);

        return cellsNext;
    }

    private void generateCellsTransition(int i, int j,Cell[][] cells, Cell[][] cellsNext){
        int alives = neighborAlives(i, j, cells);
        boolean state = false;

        state = cells[i][j].isState() ? (alives >= 2 && alives <= 3) : (alives == 3);

        cellsNext[i][j].setState(state);
    }

    private int neighborAlives(int i, int j, Cell[][] cells) {
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
