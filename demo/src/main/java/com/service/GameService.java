package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.model.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameService {
    private int size;

    public boolean[][] generateInitial() {
        boolean[][] cells = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = ThreadLocalRandom.current().nextBoolean();
        return cells;
    }

    private void fillboolean(boolean[][] cell){
       for(int i=0; i<size; i++) for(int j=0; j<size; j++) cell[i][j] = false;
    }

    public boolean[][] generateTransition(boolean[][] cells) throws InterruptedException{
        boolean[][] cellsNext = new boolean[size][size];
        fillboolean(cellsNext);

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        int aux = size / threads;

        for (int k = 0; k < threads; k++) {
            final int threadIndex = k;

            executor.execute(() -> {
                int start = threadIndex * aux;
                int end = (threadIndex == threads - 1) ? size : (threadIndex + 1) * aux;
                for (int i = start; i < end; i++) for (int j = 0; j < size; j++) generatebooleansTransition(i, j, cells, cellsNext);
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return cellsNext;
    }

    private void generatebooleansTransition(int i, int j,boolean[][] cells, boolean[][] cellsNext){
        int alives = neighborAlives(i, j, cells);
        boolean state = false;

        state = cells[i][j] ? (alives >= 2 && alives <= 3) : (alives == 3);

        cellsNext[i][j] = state;
    }

    private int neighborAlives(int i, int j, boolean[][] cells) {
        int alives = 0;
        List<Position> neighbors = neighbors(i,j);

        for(Position p : neighbors) if(cells[p.getX()][p.getY()]) alives++;

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
