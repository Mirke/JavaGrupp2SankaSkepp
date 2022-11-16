package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;

import java.util.Collections;

/*
    Karins MainRemain har flyttats hit
 */
public class ComputerLogic {

    // Attributes
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    private MyParceValue myParceValue = new MyParceValue();
    int control = 0;

    // Constructors
    public ComputerLogic() {

    }

    public String ifRecievingi() {
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots

        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        String text = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots


         /* int x = 0;
                int y = 0;
                int k = (int) (Math.random()* enemyPositions.length); //plocka ut enemyXY på position k, dela upp k i x och y
                if (k >= 10) { x = k/10; y = k%10;}
                else if (k<10) {x = 0; y = k;}
                enemyGameBoard.hitList.add(enemyGameBoard.enemyXY[x][y].getXyValue());  // add new XyValue to hitList
                enemyXY[x][y].wasHit = true;
                nextXY = enemyXY[x][y].getXyValue();*/

        return text;
    }

    // Methods
    public String iForStartOfRound() {
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots

        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));

        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        String text = "i shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots


         /* int x = 0;
                int y = 0;
                int k = (int) (Math.random()* enemyPositions.length); //plocka ut enemyXY på position k, dela upp k i x och y
                if (k >= 10) { x = k/10; y = k%10;}
                else if (k<10) {x = 0; y = k;}
                enemyGameBoard.hitList.add(enemyGameBoard.enemyXY[x][y].getXyValue());  // add new XyValue to hitList
                enemyXY[x][y].wasHit = true;
                nextXY = enemyXY[x][y].getXyValue();*/

        return text;
    }

    public String hForHit(ControlOfInput controlOfInput) {
        // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
        // find position next to XyValue in hitList, will be  next shot
        /*
        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
        */

        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));;
        String nextXY = "";


        if(y != 0 && y != 9 && x != 0 && x != 9){
            if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                x++;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                x--;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                y++;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit){
                y--;
            }

        }else{
            if (x == 0 && y == 0) {
                //Check upper left corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    y++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                }

            }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                    }
                } else if (y == 0) { // Check positions along y = 0
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][y].wasHit) {
                    x++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                    y++;
                }

            } else if (y == 9) { //Check border along y = 9
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;

                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;

                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    }
            }
        }

        nextXY = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        //myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getRemainingXYspots().nextXY);  // add new XyValue to hitList
          // remove xyValue from remainingXYspots


        return nextXY;
    }

    public String mForMiss(ControlOfInput controlOfInput) {
        System.out.println("inne i mformiss");
        String nextXY = "";
        myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);

        boolean works = false;
        if (myStringCoordinates.getEnemyGameBoard().hitList.size() >= 1) {
            System.out.println("inne if");
            int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
            int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));

                if (x == 0 && y == 0) {
                    //Check upper left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                        works = true;
                    }

                }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                        works = true;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                        works = true;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                        works = true;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                        works = true;
                    }
                }else if (y == 0) { // Check positions along y = 0
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                        y++;
                        works = true;
                    }

                }else if (y == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit){
                        y--;
                        works = true;
                    }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                        works = true;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                        works = true;
                    }
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                    x++;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                    x--;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                    y++;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit){
                    y--;
                    works = true;
                }

                if(!works){
                x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));
                y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));

                if (x == 0 && y == 0) {
                    //Check upper left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    }

                }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                        x--;
                    }
                }else if (y == 0) { // Check positions along y = 0
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                        y++;
                    }

                }else if (y == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit){
                        y--;
                    }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                        x++;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    }
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit){
                    x++;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit){
                    x--;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit){
                    y++;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit){
                    y--;
                }
            }
            nextXY = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());


/*
            myStringCoordinates.getEnemyGameBoard().hitList.remove( myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);   // clear hitList
            Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));  // add new XyValue to hitList
            text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots*/

        } else if (myStringCoordinates.getEnemyGameBoard().hitList.size() <= 1) {
            System.out.println("inne i if else");
            myStringCoordinates.getEnemyGameBoard().hitList.clear();
            nextXY =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

            int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
            int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        }else{
            System.out.println("inne i else");
            myStringCoordinates.getEnemyGameBoard().hitList.clear();
            nextXY =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

            int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
            int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        }
        System.out.println("returnerar" + nextXY);
        return nextXY;
    }

    public String sForSink(ControlOfInput controlOfInput) {
        // EnemyBoats.length tells which boat was sunk
        // logic to clear safe points around boat - add positions to guardedSpots[]
        // remove boat from ListEnemyBoats[]
        // remove guardedSpots from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots

        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);

        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots

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
