package com.grupp2.sankaskepp.TestStart;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.GameBoard;

import java.util.Iterator;
import java.util.List;

public class ComputerAI {
    // Attributes
    private String shot;
    private Position position;
    private GameBoard gameBoard;
    private Boat boat;
   // private String shot;

    // Constructors
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
        boolean destroyed = false;
        if(position.getAllShipCoordinates().isEmpty()){
            destroyed = true;
        }

        return destroyed;
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
        // returnerar sant om listan med skepp koordinater innehåller samma värde som "shot"
        if(position.getAllShipCoordinates().contains(isAHit)){
            remove();
        }
        return position.getAllShipCoordinates().contains(isAHit);

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
