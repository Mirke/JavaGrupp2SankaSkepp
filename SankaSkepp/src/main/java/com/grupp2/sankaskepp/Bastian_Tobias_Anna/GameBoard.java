package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.CreateAndSetBoats.Fleet;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Parent {

    /**
     * Author: Tobias Johansson
     */
    private VBox vRow = new VBox();
    private TheCell cell;
    private Fleet fleet;
    private MyParceValue myParceValue = new MyParceValue();
    private final int[] ALLBOATS = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};

    // Constructor
    // Creates a empty board for enemy
    public GameBoard() {
        // create board
        for (int y = 0; y < 10; y++) {
            HBox hRow = new HBox();
            for (int x = 0; x < 10; x++) {
                TheCell cell = new TheCell(x, y, this);
                hRow.getChildren().add(cell);
            }
            vRow.getChildren().add(hRow);
        }
        getChildren().add(vRow);

    }

    // creates a board for you with boats on
    public GameBoard(Fleet fleet) {

        this.fleet = fleet;
        // create board
        for (int y = 0; y < 10; y++) {
            HBox hRow = new HBox();
            for (int x = 0; x < 10; x++) {
                TheCell cell = new TheCell(x, y, this);
                hRow.getChildren().add(cell);
            }
            vRow.getChildren().add(hRow);
        }
        getChildren().add(vRow);

        // sends coordinates to get parced to ints
        placeBoats();
    }

    // Methods

    // when a boat is placed its colored lightgray
    private void placeBoats() {
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < ALLBOATS[i]; j++) {

                String shot = fleet.getBoats()[i].getPosition().get(j);

                int x = myParceValue.stringToXint(shot);
                int y = myParceValue.stringToYint(shot);

                this.cell = getTheCell(x, y);
                cell.setFill(Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);
            }
        }
    }

    public void boatIsHit(String shot) {
        int x = myParceValue.stringToXint(shot);
        int y = myParceValue.stringToYint(shot);
        this.cell = getTheCell(x, y);
        cell.setFill(Color.PURPLE);
        cell.setStroke(Color.BLACK);
    }

    public void boatIsMiss(String shot) {
        int x = myParceValue.stringToXint(shot);
        int y = myParceValue.stringToYint(shot);
        this.cell = getTheCell(x, y);
        cell.setFill(Color.DARKBLUE);
        cell.setStroke(Color.BLACK);
    }

    private TheCell getTheCell(int x, int y) {
        return (TheCell) ((HBox) vRow.getChildren().get(y)).getChildren().get(x);
    }
}