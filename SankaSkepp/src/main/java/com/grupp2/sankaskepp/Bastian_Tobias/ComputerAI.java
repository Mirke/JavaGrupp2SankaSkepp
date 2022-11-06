package com.grupp2.sankaskepp.Bastian_Tobias;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;


import java.util.Iterator;
import java.util.List;

/*
    Author: Tobias
 */

public class ComputerAI {
    // Attributes
    private String shot;
    private Position position;
    private GameBoard gameBoard;
    private Boat boat;
   // private String shot;

    // Constructors
    public ComputerAI(){}
    public ComputerAI(Boat boat) {
        this.boat = boat;
        position = new Position(boat);
    }

    // methods
    public void keepShooting() {
        Iterator i = position.getAllCoordinates().listIterator();

        while (i.hasNext()) {
            if (position.getAllShipCoordinates().isEmpty()) {
                System.out.println("i lost");
                return;
            } else{
                position.printList(position.getAllShipCoordinates());
            }
            shot();

        }
    }
    public boolean isAnyShipLeft(){

        return position.getAllShipCoordinates().isEmpty();
    }

    public String shot() {
        // skaka fram en random koordinat
        position.shuffleList(position.getAllCoordinates());
        // index 0 i listan är ett skott
        this.shot = position.getAllCoordinates().get(0);

        // ta alltid bort index 0 ur listan med alla koordinater
        position.remove(position.getAllCoordinates());

        return shot;
    }

    public boolean isHit(String isAHit) {
        // returnerar sant om listan med skepp koordinater innehåller samma värde som "isAHit"
        boolean hit = false;
        if(position.getAllShipCoordinates().contains(isAHit)){
            position.remove(position.getAllShipCoordinates(),isAHit);
            hit = true;
        }
        return hit;

    }
    public void remove(){
        // om träff, ta bort det värdet ur listan med skepp
        position.remove(position.getAllShipCoordinates(),shot);

    }

    // getter n setters
    public void getAllShipCoordinates(){
        position.printList(position.getAllShipCoordinates());
    }
    public List<String> getAllCoordinates(){
       return position.getAllCoordinates();
    }
}
