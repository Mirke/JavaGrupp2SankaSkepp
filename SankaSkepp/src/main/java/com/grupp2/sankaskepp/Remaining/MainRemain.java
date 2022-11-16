package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainRemain {/*
    // tobias test - finns metod som tar in String till enemyShot (längst ner)
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    // tobias test - ändrade så main inte är static
    public void main() {


        //Logik för nästa skott, beroende på var föregående skott träffade 123

        Boat EnemyBoats = new Boat();

        //while EnemyBoats[] == !0; - formulera logiken mer korrekt
        switch (Character.toString(this.enemyShot.charAt(0))) {
            case "i": {
                Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
                myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().get(0));  // add new XyValue to hitList
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
                break;
            }

            case "m": {

                if (myStringCoordinates.getEnemyGameBoard().hitList.size() < 2) {
                    myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
                    Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
                    myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
                    myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
                } else if (myStringCoordinates.getEnemyGameBoard().hitList.size() > 1) { //använd logik för nästa skott,
                    myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1); //ta bort värdet för sista index
                }
                break;

            }

            case "h": {

                // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
                // find position next to XyValue in hitList, will be  next shot

                if (x = 0) {
                    if (y = 0) {
                        if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[1][0].wasHit) {
                            nextXY = "0a";
                        else{
                                nextXY = "1b";
                            }
                        }
                    } else if (y = 9) {
                        if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[1][9].wasHit) {
                            nextXY = "0i";
                        else{
                                nextXY = "9b";
                            }
                        }
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[0][y++].wasHit) {
                        y++;
                        nextXY = (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[0][y].getXyValue());
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[0][y - 1].wasHit) {
                        y--;
                        nextY = (myStringCoordinates.getRemainingEnemyPositions()[0][y].getXyValue());
                    } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[1][y].getXyValue());
                }


                if (x = 9) {
                    if (y = 0) {
                        if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[8][0].wasHit) {
                            nextXY = "9b";
                        else{
                                nextXY = "8a";
                            }
                        }
                    } else if (y = 9) {
                        if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[8][9].wasHit) {
                            nextXY = "9i";
                        else{
                                nextXY = "8j";
                            }
                        }
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[9][y++].wasHit) {
                        y++;
                        nextXY = (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[9][y].getXyValue());
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[9][y - 1].wasHit) {
                        y--;
                        nextY = (myStringCoordinates.getRemainingEnemyPositions()[9][y].getXyValue());
                    } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[9][y].getXyValue());

                }

                if (y = 0) {
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][0].wasHit) {
                        x++;
                        nextXY = (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][0].getXyValue());
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][0].wasHit) {
                        x--;
                        nextY = (myStringCoordinates.getRemainingEnemyPositions()[x][0].getXyValue());
                    } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[x][1].getXyValue());
                }

                if (y = 9) {
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][9].wasHit) {
                        x++;
                        nextXY = (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][9].getXyValue());
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][9].wasHit) {
                        x--;
                        nextY = (myStringCoordinates.getRemainingEnemyPositions()[x][9].getXyValue());
                    } else nextXY = (myStringCoordinates.getRemainingEnemyPositions()[x][8].getXyValue());
                }



                myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().nextXY);  // add new XyValue to hitList
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(nextXY);  // remove xyValue from remainingXYspots
                break;
            }

            case "s": {

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
        public void takeStringLetter (String letter){
            this.enemyShot = letter;
            main();
        }*/
    }

