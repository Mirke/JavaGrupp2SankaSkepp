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

        return text;
    }

    public String hForHit(ControlOfInput controlOfInput) {
        String nextXY = "";
        //last shot was a hit -> set hasShip true
        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setHasShip(true); //Keep track on succesful hits!

        // Check hitList>1 guard positions around >1 hit

        if (myStringCoordinates.getEnemyGameBoard().hitList.size() > 1) {
            if (y != 0 && y != 9 && x != 0 && x != 9) {
                if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].hasShip) {  //Guard positions above and under hits
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);

                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());


                } else if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].hasShip) {  //Guard positions above and under hits
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].setWasHit(true);

                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].getXyValue());


                } else if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].hasShip) {  //Guard positions to the right and to the left
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].setWasHit(true);

                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].getXyValue());


                } else if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].hasShip) {  //Guard positions to the right and to the left
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                    myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].setWasHit(true);

                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());
                    myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].getXyValue());

                }

            } else if (x == 0 && y == 0) {  //upper left corner
                    if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].hasShip) {  //Boat horizontal, guard positions under
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);

                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].getXyValue());
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());
                    } else if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].hasShip) {  //Boat transversal, guard positions to the right
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);

                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].getXyValue());
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());
                    }


                } else if (y == 9 && x == 0) { //lower left corner
                    if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].hasShip) {  //Boat horizontal, guard positions
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].getXyValue());
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());

                    } else if (myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].hasShip) {  //Boat transversal, guard positions
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].getXyValue());
                        myStringCoordinates.getEnemyGameBoard().guardedSpots.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());

                    }

                }




            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().guardedSpots);

        }




    // find position next to XyValue in hitList, will be  next shot
        /*
        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
        */



            if(y !=0&&y !=9&&x !=0&&x !=9)

    {
        if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
            x++;
        } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
            x--;
        } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
            y++;
        } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
            y--;
        }

    } else

    {
        if (x == 0 && y == 0) {
            //Check upper left corner
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                x++;
            }

        } else if (y == 9 && x == 0) { // Check lower left corner
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                x++;
            }
            //Check if border position along x = 0
        } else if (x == 9 && y == 0) {

            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;
            }

        } else if (y == 9 && x == 9) { //Check lower right corner
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;
            }
        } else if (y == 0) { // Check positions along y = 0
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x++][y].wasHit) {
                x++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
            }

        } else if (y == 9) { //Check border along y = 9
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                x++;

            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;

            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
            }
        } else if (x == 0) {
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                x++;
            }
        } else if (x == 9) { //Check border along y = 9
            if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;
            }
        }

    }

    nextXY =" shot ".

    concat(myStringCoordinates.getEnemyGameBoard().

    getRemainingEnemyPositions()[x][y].

    getXyValue());
            myStringCoordinates.getEnemyGameBoard().

    getRemainingEnemyPositions()[x][y].

    setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().

    getRemainingEnemyPositions()[x][y].

    getXyValue());
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().

    getRemainingEnemyPositions()[x][y].

    getXyValue());

    // add new xyValue to hitList
    // remove xyValue from remainingXYspots
            return nextXY;


}

    public String mForMiss(ControlOfInput controlOfInput) {
        System.out.println("inne i mformiss");
        String nextXY = "";
        myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);
        //Removes last shot (miss), check around latest hit for new possible shot.

        //We need logic in "hasShip" to reduce number of possibilities.
        //With two "hasShip" in a row we can avoid unnecessary shots.

        boolean works = false;
        if (myStringCoordinates.getEnemyGameBoard().hitList.size() >= 1) {
            //Logic for only one previous hit from here, same as after first hit.
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

            } else if (y == 9 && x == 0) { // Check lower left corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                    works = true;
                }
                //Check if border position along x = 0
            } else if (x == 9 && y == 0) {

                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    y++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                    works = true;
                }

            } else if (y == 9 && x == 9) { //Check lower right corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                    works = true;
                }
            } else if (y == 0) { // Check positions along y = 0
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    y++;
                    works = true;
                }

            } else if (y == 9) { //Check border along y = 9
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                    works = true;
                }
            } else if (x == 0) {
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    y++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                    works = true;
                }
            } else if (x == 9) { //Check border along y = 9
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
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                x++;
                works = true;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                x--;
                works = true;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                y++;
                works = true;
            } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                y--;
                works = true;
            }

            if (!works) {
                x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));
                y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));

                if (x == 0 && y == 0) {
                    //Check upper left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    }

                } else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    }
                } else if (y == 0) { // Check positions along y = 0
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    }

                } else if (y == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    }
                } else if (x == 0) {
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x++;
                    }
                } else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x--;
                    }
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    x++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    x--;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    y++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    y--;
                }
            }
            nextXY = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());


        }  else {
            System.out.println("inne i else");
            myStringCoordinates.getEnemyGameBoard().hitList.clear();
            nextXY = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

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
        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));

        String nextXY = "";
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setHasShip(true); //Keep track on succesful hits!
        //myStringCoordinates.getEnemyBoatList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][x].getXyValue());//Addera till listEnemyBoats[]


        // EnemyBoats.length tells which boat was sunk
        // logic to clear safe points around boat - find spots, set wasHit, add to guardedSpots
        // remove boat from enemyBoatList[]
        // remove guardedSpots from remainingXYspots
        myStringCoordinates.getEnemyGameBoard().hitList.clear();   // clear hitList
        //Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        //Unnecessary shuffle random list


        //Pick next random shot from remainingXYspots
        x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
        y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
        myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);

        String text = " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
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

