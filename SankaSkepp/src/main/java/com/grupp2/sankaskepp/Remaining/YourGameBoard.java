package com.grupp2.sankaskepp.Remaining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class YourGameBoard extends NewGameBoard{

    //2D-array för koordinaterna

    private XYposition[][] yourPositions = new XYposition[10][10];


    //Getters & Setters

    public XYposition[][] getYourPositions() {
        return yourPositions;
    }

    public void setYourPositions(XYposition[][] yourPositions) {
        this.yourPositions = yourPositions;
    }

}
