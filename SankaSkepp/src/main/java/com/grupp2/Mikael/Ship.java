package com.grupp2.Mikael;

public abstract class Ship implements TakeDamage {
    int size;
    int lifePoints;
    boolean isAlive=true;
    Cell[] cells;

    public Ship() {
    }

    public Ship(int size, int lifePoints, boolean isAlive, Cell[] cells) {
        this.size = size;
        this.lifePoints = lifePoints;
        this.isAlive = isAlive;
        this.cells = cells;
    }

    @Override
    public void takeDamage(int damage) {
        // TODO
    }

    @Override
    public void checkIfAlive() {
        // TODO
    }
}
