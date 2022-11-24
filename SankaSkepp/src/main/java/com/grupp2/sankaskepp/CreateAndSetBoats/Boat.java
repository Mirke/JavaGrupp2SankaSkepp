package com.grupp2.sankaskepp.CreateAndSetBoats;
import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Bastian
 */

public class Boat {

    /*-----------------------------------------------------------------------------------------------------------------
     * Variabler
     ------------------------------------------------------------------------------------------------------------------*/
    private ArrayList<String> position = new ArrayList();
    private int size;
    private String name;

    /*-----------------------------------------------------------------------------------------------------------------
     * Konstruktorer
     ------------------------------------------------------------------------------------------------------------------*/
    public Boat(){

    }
    public Boat(int size, String name){
        this.size = size;
        this.name = name;
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * Getters och Setters
     ------------------------------------------------------------------------------------------------------------------*/
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public ArrayList<String> getPosition() {
        return position;
    }

    public void setPosition(ArrayList <String> position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}


