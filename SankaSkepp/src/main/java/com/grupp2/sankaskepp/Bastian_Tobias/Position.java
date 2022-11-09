package com.grupp2.sankaskepp.Bastian_Tobias;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Position {
    // Attributes
    private String[][] string2DArray;
    private List<String> allCoordinates;
    private List<String> allShipCoordinates;
    private Boat boat;
    private final int X_HORISONTAL = 10;
    private final int Y_VERTICAL = 10;
    private final int[] ALLBOATS = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};

    // Constructors

    public Position(){
        allCoordinates = new ArrayList<>();

        for (int i = 0; i < X_HORISONTAL; i++) {

            for (int j = 0; j < Y_VERTICAL; j++) {
                // 2D array
              //  string2DArray[i][j] = xString(i).concat(yString(j));
                // alla koordinater i en lista
                allCoordinates.add(yString(i).concat(xString(j)));
            }
        }
    }
    public Position(Boat boat) {
        this.boat = boat;

        string2DArray = new String[X_HORISONTAL][Y_VERTICAL];
        allCoordinates = new ArrayList<>();

        for (int i = 0; i < X_HORISONTAL; i++) {

            for (int j = 0; j < Y_VERTICAL; j++) {
                // 2D array
                string2DArray[i][j] = xString(i).concat(yString(j));
                // alla koordinater i en lista
                allCoordinates.add(yString(i).concat(xString(j)));
            }
        }

        // copy down all ship coordinates
        listOfAllShipCoordinates();
    }

    // Methods
    public void yUp(int y) {
        // y + 1
    }

    public void yDown(int y) {
        // y - 1
    }

    public void xLeft(int x) {
        // x - 1
    }

    public void xRight(int x) {
        // y + 1
    }
    public void topLeft(){
        // x + 1, y + 1
    }
    public void topRight(){
        // x + 1, y + 1
    }
    public void bottomLeft(){
        // x - 1, y - 1
    }
    public void bottomRight(){
        // x + 1, y - 1
    }

    public void remove(List<String> str) {
        str.remove(0);
    }

    public void remove(List<String> list, String str) {
        list.remove(str);
    }

    public void shuffleList(List<String> list) {
        Collections.shuffle(list);
    }

    public void listOfAllShipCoordinates() {
        allShipCoordinates = new ArrayList<>();
        for (int i = 0; i < ALLBOATS.length; i++) {
            for (int j = 0; j < ALLBOATS[i]; j++) {
                allShipCoordinates.add(this.boat.getBoats()[i].getPosition().get(j));
            }
        }
    }

    public void printList(List<String> theList) {
        Iterator iterator = theList.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    private String yString(int y) {
        String str = "";
        switch (y) {
            case 0:
                str = "0";
                break;
            case 1:
                str = "1";
                break;
            case 2:
                str = "2";
                break;
            case 3:
                str = "3";
                break;
            case 4:
                str = "4";
                break;
            case 5:
                str = "5";
                break;
            case 6:
                str = "6";
                break;
            case 7:
                str = "7";
                break;
            case 8:
                str = "8";
                break;
            case 9:
                str = "9";
                break;
        }
        return str;
    }

    private String xString(int x) {
        String str = "";
        switch (x) {
            case 0:
                str = "a";
                break;
            case 1:
                str = "b";
                break;
            case 2:
                str = "c";
                break;
            case 3:
                str = "d";
                break;
            case 4:
                str = "e";
                break;
            case 5:
                str = "f";
                break;
            case 6:
                str = "g";
                break;
            case 7:
                str = "h";
                break;
            case 8:
                str = "i";
                break;
            case 9:
                str = "j";
                break;
        }

        return str;
    }

    // getter and setters

    public List<String> getAllCoordinates() {
        return allCoordinates;
    }

    public List<String> getAllShipCoordinates() {
        return allShipCoordinates;
    }
}
