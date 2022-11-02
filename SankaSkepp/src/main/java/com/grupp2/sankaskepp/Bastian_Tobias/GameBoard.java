package com.grupp2.sankaskepp.Bastian_Tobias;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Parent {
    private VBox vRow = new VBox();
    private TheCell cell;
    private Boat boat;
    List<String> position = new ArrayList<>();

    // Constructor
    public GameBoard(Boat boat) {

        this.boat = boat;
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

        parceStringCoordinates(boat);
    }


    public void parceStringCoordinates(Boat boat) {

        int[] allBoats = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};

        {
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < allBoats[i]; j++) {

                    String str = boat.getBoats()[i].getPosition().get(j);

                    char xChar = str.charAt(0);
                    char yChar = str.charAt(1);

                    int x = Integer.parseInt(Character.toString(xChar));

                    int y = 0;
                    switch (yChar) {

                        case 'a':
                            y = 0;
                            break;
                        case 'b':
                            y = 1;
                            break;
                        case 'c':
                            y = 2;
                            break;
                        case 'd':
                            y = 3;
                            break;
                        case 'e':
                            y = 4;
                            break;
                        case 'f':
                            y = 5;
                            break;
                        case 'g':
                            y = 6;
                            break;
                        case 'h':
                            y = 7;
                            break;
                        case 'i':
                            y = 8;
                            break;
                        case 'j':
                            y = 9;
                            break;

                    }
                    placeBoats(x, y);
                }
            }
        }
    }
    public void placeBoats(int x, int y) {

        TheCell cell = getTheCell(x, y);
        cell.aBoat = true;
        cell.setFill(Color.LIGHTGRAY);
        cell.setStroke(Color.BLACK);
    }

    private TheCell getTheCell(int x, int y) {
        return (TheCell) ((HBox) vRow.getChildren().get(y)).getChildren().get(x);
    }
}