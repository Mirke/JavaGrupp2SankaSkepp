package com.grupp2.Mikael;

public class Cell {
    private String xy;
    boolean isRuined = false;
    public Cell(String xy ) {
        this.xy = xy;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "xy='" + xy + '\'' +
                '}';
    }

    public void destroyCell(){
        this.isRuined = true;
    }

    public void regenerateCell(){
        this.isRuined = false;
    }
}
