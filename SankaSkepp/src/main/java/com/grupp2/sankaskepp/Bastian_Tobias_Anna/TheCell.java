package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TheCell extends Rectangle {
    /**
     * Author: Tobias Johansson
     */
    public int x, y;
    private GameBoard board;

    public TheCell(int x, int y, GameBoard board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.AQUA);
        setStroke(Color.BLACK);
    }
}