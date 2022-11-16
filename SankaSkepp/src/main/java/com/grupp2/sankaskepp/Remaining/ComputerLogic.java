package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.Collections;

/*
    Karins MainRemain har flyttats hit
 */
public class ComputerLogic {

    // Attributes
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    private MyParceValue myParceValue = new MyParceValue();

    // Constructors
    public ComputerLogic() {

    }

    // Methods
    public void iForStartOfRound() {
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().get(0));  // add new XyValue to hitList
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

         /* int x = 0;
                int y = 0;
                int k = (int) (Math.random()* enemyPositions.length); //plocka ut enemyXY på position k, dela upp k i x och y
                if (k >= 10) { x = k/10; y = k%10;}
                else if (k<10) {x = 0; y = k;}
                enemyGameBoard.hitList.add(enemyGameBoard.enemyXY[x][y].getXyValue());  // add new XyValue to hitList
                enemyXY[x][y].wasHit = true;
                nextXY = enemyXY[x][y].getXyValue();*/

    }

    public void hForHit() {
        // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
        // find position next to XyValue in hitList, will be  next shot

        int x = 0;
        int y = 0;
        String nextXY = " ";

        if (x == 0) {
            if (y == 0) { //Check upper left corner
                if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[1][0].wasHit) {
                    y++;
                } else {
                    x++;
                }

            } else if (y == 9) { // Check lower left corner
                if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[1][9].wasHit) {
                    y--;
                } else {
                    x++;

                }
                //Check if border position along x = 0
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[0][y + 1].wasHit) {
                y++; //Check if next position down is used

            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--; //Check if next position up is used

            } else
                x++; //Next position to the right

        }


        if (x == 9) {
            if (y == 0) { //Check upper right corner
                if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[8][0].wasHit) {
                    y++; //Next position one step down

                } else x--; //Next position one step to the left


            } else if (y == 9) { //Check lower right corner
                if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[8][9].wasHit) { //Check to the left if used
                    y--;
                } else x--;


            }
            //Check if border position along x = 9
            else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[9][y++].wasHit) {
                y++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[9][y--].wasHit) {
                y--;
                nextXY = (myStringCoordinates.getRemainingEnemyPositions()[9][y].getXyValue());
            } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[9][y].getXyValue());

        }
        if (y == 0) { // Check positions along y = 0
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][0].wasHit) {
                x++;

            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x--][0].wasHit) {
                x--;

            } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[x][1].getXyValue());
        }

        if (y == 9) { //Check border along y = 9
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][9].wasHit) {
                x++;

            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][9].wasHit) {
                x--;

            } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[x][8].getXyValue());
        }
        nextXY = (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].wasHit = true;
        //myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().nextXY);  // add new XyValue to hitList
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(nextXY);  // remove xyValue from remainingXYspots


    }

    public void mForMiss() {
        if (myStringCoordinates.getEnemyGameBoard().hitList.size() < 2) {
            myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
            Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
        } else if (myStringCoordinates.getEnemyGameBoard().hitList.size() > 1) { //använd logik för nästa skott,
            myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1); //ta bort värdet för sista index
        }

    }

    public void sForSink() {
        // EnemyBoats.length tells which boat was sunk
        // logic to clear safe points around boat - add positions to guardedSpots[]
        // remove boat from ListEnemyBoats[]
        // remove guardedSpots from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

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
