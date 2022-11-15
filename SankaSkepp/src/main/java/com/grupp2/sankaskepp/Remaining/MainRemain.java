package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainRemain {
    // tobias test - finns metod som tar in String till enemyShot (längst ner)
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    // tobias test - ändrade så main inte är static
    public void main() {


        //Logik för nästa skott, beroende på var föregående skott träffade 123

        Boat EnemyBoats = new Boat();

        //while EnemyBoats[] == !0; - formulera logiken mer korrekt

        if (Character.toString(this.enemyShot.charAt(0)).equals("i")) {

            Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().get(0));  // add new XyValue to hitList
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots


        } else if (Character.toString(this.enemyShot.charAt(0)).equals("m")) {

            if (myStringCoordinates.getEnemyGameBoard().hitList.size() < 2) {
                myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
                Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
                myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
            } else if (myStringCoordinates.getEnemyGameBoard().hitList.size() > 1) { //använd logik för nästa skott,
                myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1); //ta bort värdet för sista index
            }


        } else if (Character.toString(this.enemyShot.charAt(0)).equals("h")) {

            // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
            // find position next to XyValue in hitList, will be  next shot
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        } else if (Character.toString(enemyShot.charAt(0)).equals("s")) {

            // EnemyBoats.length tells which boat was sunk
            // logic to clear safe points around boat - add positions to guardedSpots[]
            // remove boat from ListEnemyBoats[]
            // remove guardedSpots from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
            Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        }
    }

    // tobias test - hämtar från mickes protokoll
    // sedan startar main metoden här i klassen
    public void takeStringLetter(String letter) {
        this.enemyShot = letter;
        main();
    }
}

