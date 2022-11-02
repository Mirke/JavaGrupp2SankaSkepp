package com.grupp2.sankaskepp;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TheCell extends Rectangle {

    public int x, y;
    // public boolean wasShot = false;
    private GameBoard board;

    public TheCell(int x, int y, GameBoard board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.AQUA);
        setStroke(Color.WHITE);
    }
}