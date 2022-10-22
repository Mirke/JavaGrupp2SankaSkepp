package com.grupp2.sankaskepp;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class GameBoard extends Parent {
    private VBox vRow = new VBox();
    private TheCell cell;
    private boolean attacker = false;
    private boolean defender = false;

    public GameBoard(boolean attacker, boolean defender) {
        this.attacker = attacker;
        this.defender = defender;

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
}