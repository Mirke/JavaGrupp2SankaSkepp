package com.grupp2.Mikael;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship   {
    int size;
    boolean isRuined=false;
    List<Cell> cells;

    public Ship() {
        this.cells = new ArrayList<>();
    }

    public List<Cell> getCells(){
       return this.cells;
    }
}
