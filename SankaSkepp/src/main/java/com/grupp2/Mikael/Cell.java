package com.grupp2.Mikael;

public class Cell {
    private String xy;
    public Cell(String xy ) {
        this.xy = xy;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "xy='" + xy + '\'' +
                '}';
    }
}
