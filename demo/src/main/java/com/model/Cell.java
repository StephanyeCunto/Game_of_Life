package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cell {
    private boolean state;
    private Position position;
    private int alives;
}