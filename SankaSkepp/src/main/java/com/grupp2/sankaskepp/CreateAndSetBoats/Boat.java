package com.grupp2.sankaskepp.CreateAndSetBoats;
import java.util.ArrayList;
import java.util.Random;


public class Boat {

    private ArrayList<String> position = new ArrayList();

    private int size;
    private String name;
    private Boat[] boats;
    Random random = new Random();


    //Konstruktorer för båtarna
    public Boat(){

    }
    public Boat(int size, String name){
        this.size = size;
        this.name = name;
    }


    //Skapande av alla båtar
    public void createBoats(){
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

    //Getters och Setters
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

    public Boat[] getBoats() {
        return boats;
    }
    }


