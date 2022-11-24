package com.grupp2.sankaskepp.CreateAndSetBoats;

/**
 * Author: Bastian
 */
public class Fleet {
    /*-----------------------------------------------------------------------------------------------------------------
     * Variabler
     ------------------------------------------------------------------------------------------------------------------*/
    private Boat[] boats;

    /*-----------------------------------------------------------------------------------------------------------------
     * Konstruktor
     ------------------------------------------------------------------------------------------------------------------*/
    public Fleet(){
        Boat boat1 = new Boat(5, "Hangarfartyg");
        Boat boat2 = new Boat(4, "Kryssare");
        Boat boat3 = new Boat(4, "Kryssare");
        Boat boat4 = new Boat(3, "Slagskepp");
        Boat boat5 = new Boat(3, "Slagskepp");
        Boat boat6 = new Boat(3, "Slagskepp");
        Boat boat7 = new Boat(2, "Ubåt");
        Boat boat8 = new Boat(2, "Ubåt");
        Boat boat9 = new Boat(2, "Ubåt");
        Boat boat10 = new Boat(2, "Ubåt");

        //Gör att man kan få båtarnas storlek och position genom en loop och en getter istället för väldigt många rader kod
        boats = new Boat[]{boat1, boat2, boat3, boat4, boat5, boat6, boat7, boat8, boat9, boat10};
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * Getters och Setters
     ------------------------------------------------------------------------------------------------------------------*/
    public Boat[] getBoats() {
        return boats;
    }

    public void setBoats(Boat[] boats) {
        this.boats = boats;
    }
}
