package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.Collections;

/*
    Karins MainRemain har flyttats hit
 */
public class ComputerLogic {

    // Attributes
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    // Constructors
    public ComputerLogic() {

    }

    // Methods
    public String iForStartOfRound() {
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().get(0));// add new XyValue to hitList
        String text = " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        return text;
    }

    public String hForHit() {
        // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
        // find position next to XyValue in hitList, will be  next shot
        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        return text;
    }

    public String mForMiss() {
        String text = "";
        if (myStringCoordinates.getEnemyGameBoard().hitList.size() < 2) {
            myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
            Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
            text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
        } else if (myStringCoordinates.getEnemyGameBoard().hitList.size() > 1) { //använd logik för nästa skott,
            myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1); //ta bort värdet för sista index
        }
        return text;
    }

    public String sForSink() {
        // EnemyBoats.length tells which boat was sunk
        // logic to clear safe points around boat - add positions to guardedSpots[]
        // remove boat from ListEnemyBoats[]
        // remove guardedSpots from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList

        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        return text;
    }

    // tobias test - hämtar från mickes protokoll
    // sedan startar main metoden här i klassen
    public void takeStringLetter(String letter) {
        this.enemyShot = letter;
    }
    // Getters

    public String getEnemyShot() {
        return enemyShot;
    }

    public MyStringCoordinates getMyStringCoordinates() {
        return myStringCoordinates;
    }
}
