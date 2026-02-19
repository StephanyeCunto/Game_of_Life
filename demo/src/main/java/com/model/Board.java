package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Cell[][] cells;
    private int size;

    public Board(int size){
        this.size = size;
        cells = new Cell[size][size];
    }

}