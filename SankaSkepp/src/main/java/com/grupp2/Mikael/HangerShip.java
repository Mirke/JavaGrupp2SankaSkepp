package com.grupp2.Mikael;

import java.util.List;

public class HangerShip extends Ship {
    public HangerShip(List<Cell> listOfFiveCells) {
        super();
        this.size = 5;
        if(listOfFiveCells.size() == 5){
            this.cells = listOfFiveCells;
        } else {
            throw new IndexOutOfBoundsException();
        }

    }
}
