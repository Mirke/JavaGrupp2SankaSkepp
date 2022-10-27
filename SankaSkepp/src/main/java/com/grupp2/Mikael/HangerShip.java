package com.grupp2.Mikael;

public class HangerShip extends Ship {
    public HangerShip(int size, int lifePoints, boolean isAlive, Cell[] cells) {
        super(size, lifePoints, isAlive, cells);
    }

    public HangerShip(Cell[] cells) {
        this.size = 5;
        this.lifePoints = 5;
        this.cells = cells;
    }
}
