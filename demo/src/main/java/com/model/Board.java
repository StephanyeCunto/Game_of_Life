package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private boolean[][] cells;
    private int size;

    public Board(int size){
        this.size = size;
        cells = new boolean[size][size];
    }

}