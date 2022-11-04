package com.grupp2.sankaskepp;

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

    // lista som håller skeppen koordinater som string
    List<String> allShipCoordinates = new ArrayList<>();

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

    // Methods
    public void parceStringShotCoordinates(boolean hit, String shot) {
        char xChar = shot.charAt(0);
        char yChar = shot.charAt(1);

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
        // skicka vidare bool och koordinater för att målas upp på brädet.
        boatIsHit(hit,x,y);
    }

    private void parceStringCoordinates(Boat boat) {

        int[] allBoats = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};

        {
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < allBoats[i]; j++) {

                    String str = boat.getBoats()[i].getPosition().get(j);

                    // add all String coordinates to list
                    allShipCoordinates.add(boat.getBoats()[i].getPosition().get(j));

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

        // iteratorForCoordinates(allShipCoordinates);
    }

    public void placeBoats(int x, int y) {

        this.cell = getTheCell(x, y);
        cell.aBoat = true;
        cell.setFill(Color.LIGHTGRAY);
        cell.setStroke(Color.BLACK);
    }

    // om cell är träffad blir den målad röd, annars mörkblå.
    private void boatIsHit(boolean hit, int x, int y) {
        if (hit) {
            this.cell = getTheCell(x, y);
            cell.aBoat = true;
            cell.setFill(Color.RED);
            cell.setStroke(Color.BLACK);
        } else {
            // miss
            this.cell = getTheCell(x, y);
            cell.aBoat = true;
            cell.setFill(Color.DARKBLUE);
            cell.setStroke(Color.BLACK);
        }
    }

    private TheCell getTheCell(int x, int y) {
        return (TheCell) ((HBox) vRow.getChildren().get(y)).getChildren().get(x);
    }
}