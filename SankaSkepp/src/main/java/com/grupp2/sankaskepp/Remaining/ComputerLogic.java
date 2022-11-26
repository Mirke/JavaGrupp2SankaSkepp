package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;

import java.util.Collections;
import java.util.List;

public class ComputerLogic  { //Karin och Tobias

    // Attributes
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringXY myStringXY = new MyStringXY();

    private MyParceValue myParceValue = new MyParceValue();

    private GuardSafeSpots guardSafeSpots = new GuardSafeSpots();

    //Hitfirst hitfirst = new Hitfirst();

    //HitHorizontal hitHorizontal = new HitHorizontal();


    int control = 0;

    // Constructors
    public ComputerLogic() {

    }

    public MyStringXY getMyStringXY() {
        return myStringXY;
    }

    public MyParceValue getMyParceValue() {
        return myParceValue;
    } //Tobias

    public GuardSafeSpots getGuardSafeSpots() {
        return guardSafeSpots;
    }

    public String ifRecievingi() {
        Collections.shuffle(myStringXY.getRemainingXYspots());  // return Random xyValue from remainingXYspots

        int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
        int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));

        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        String text = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots

        return text;
    }

    // Methods
    public String iForStartOfRound() {
        Collections.shuffle(myStringXY.getRemainingXYspots());  // return Random xyValue from remainingXYspots

        int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
        int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));


        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        String text = "i shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots

        return text;
    }

    public String hForHit(ControlOfInput controlOfInput) {
        String nextXY = "";

        int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setHasBoat(true); //Keep track on succesful hits!
        //Karin härifrån
        int xNext = x;
        int yNext = y;

        if (myStringXY.getEnemyGameBoard().hitList.size() >= 2) {

            System.out.println("_____please guard___________");

            boolean transversal = false; //find direction of boat
            int xFirstHit = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
            int yFirstHit = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
            if (x == xFirstHit) { //If x constant, boat is transversal, otherwise horizontal
                transversal = true;
            }

            secure(x, y, transversal); //Secure safe spots, Karin Logic


            if (!transversal) { //Horizontal boat
                if (x > xFirstHit) { //keep going right until stopped, else right
                    if (x == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        xNext = xFirstHit - 1;
                    } else {
                        xNext = x + 1;
                    }
                }

                if (x < xFirstHit) { //keep going left until stopped, else right
                    if (x == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        xNext = xFirstHit + 1;
                    } else {
                        xNext = x - 1;
                    }
                }


            } else { //transversal boat
                if (y > yFirstHit) { //keep going down until stopped, else up
                    if (y == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        yNext = yFirstHit - 1;
                    } else {
                        yNext = y + 1;
                    }
                }
                if (y < yFirstHit) {//keep going up until stopped, else down

                    if (y == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        yNext = yFirstHit + 1;
                    } else {
                        yNext = y - 1;
                    }
                }
            }

        } else { //hitList contains only one value, no direction yet
            System.out.println("____endast en träff_____");

            if (x == 0 && y == 0) {  //Check upper left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else xNext = x + 1;
            } else if (x == 0 && y == 9) { // Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x + 1;
            } else if (x == 9 && y == 9) { //Check lower right corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x - 1;
            } else if (x == 9 && y == 0) { //Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    xNext = x - 1;
                } else yNext = y + 1;
            } else if (x == 0) {  //Check Left column x = 0
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else yNext = y - 1;
            } else if (y == 0) { // Check Top row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else xNext = x - 1;
            } else if (y == 9) { //Check Bottom row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x - 1;
            } else if (x == 9) { //Check Right column
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    xNext = x - 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else yNext = y + 1;
            } else { //if (p >= 1 && p <= 8 && q <= 8 && q >= 1)
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;

                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    xNext = x - 1;
                } else {
                    yNext = y - 1;
                }
            }
        }

        if (xNext != x) {
            x = xNext;
        }
        if (yNext != y) {
            y = yNext;
        }

        nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // add new xyValue to hitList
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // remove xyValue from remainingXYspots

        return nextXY;
    }


    public String mForMiss(ControlOfInput controlOfInput) { //Karin
        System.out.println("inne i mformiss");
        String nextXY = "";

        myStringXY.getEnemyGameBoard().hitList.remove(myStringXY.getEnemyGameBoard().hitList.size() - 1);
        //Removes last shot (miss), check around latest hit for new possible shot.
        int x = 0;
        int y = 0;
        int newX = x;
        int newY = y;


        if (myStringXY.getEnemyGameBoard().hitList.size() == 0) { //If no previous hit, take next random value from list
            myStringXY.getEnemyGameBoard().hitList.clear();

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
            newX = x;
            newY = y;


        } else if (myStringXY.getEnemyGameBoard().hitList.size() == 1) {   //hitList contains only one value, no direction yet

            System.out.println(" inside mForMiss____single previous hit_____");

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            newX = x;
            newY = y;


            if (x == 0 && y == 0) {  //Check upper left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else newX = x + 1;
            }

            else if (x == 0 && y == 9) { // Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x + 1;
            } else if (x == 9 && y == 9) { //Check lower right corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x - 1;
            } else if (x == 9 && y == 0) { //Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    newX = x - 1;
                } else newY = y + 1;
            } else if (x == 0) {  //Check Left column x = 0
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else newY = y - 1;
            } else if (y == 0) { // Check Top row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else newX = x - 1;
            } else if (y == 9) { //Check Bottom row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x - 1;
            } else if (x == 9) { //Check Right column
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    newX = x - 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newY = y + 1;
            } else { //if (p >= 1 && p <= 8 && q <= 8 && q >= 1)
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;

                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    newX = x - 1;
                } else {
                    newY = y - 1;
                }
            }


        } else if (myStringXY.getEnemyGameBoard().hitList.size() >= 2) {

            System.out.println("inside mForMiss, >2 hits"); //Karin logic

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            newX = x;
            newY = y;

            boolean transversal = false;
            int xFirst = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
            int yFirst = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
            if (x == xFirst) {
                transversal = true;
            } //If x constant, boat is transversal


            if (!transversal) {
                    if (x > xFirst) { //stepping right, continue until stopped
                    if (x == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        newX = xFirst - 1;
                    } else {
                        newX = x + 1;
                    }
                }

                if (x < xFirst) {//stepping left, continue until stopped
                    if (x == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        newX = xFirst + 1;
                    } else {
                        newX = x - 1;
                    }
                }


            } else { //transversal boat
                    if (y > yFirst) { //stepping down, continue until stopped
                    if (y == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        newY = yFirst - 1;
                    } else {
                        newY = y + 1;
                    }
                }
                if (y < yFirst) {//stepping up, continue until stopped

                    if (y == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        newY = yFirst + 1;
                    } else {
                        newY = y - 1;
                    }
                }
            }

        }

        if (newX != x) {
            x = newX;
        }
        if (newY != y) {
            y = newY;
        }
        nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // add new xyValue to hitList
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // remove xyValue from remainingXYspots

        return nextXY;
    }


    public String sForSink(ControlOfInput controlOfInput) { //Tobias logik för gardering runt om
        String nextXY = "";
        int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setHasBoat(true); //Keep track on succesful hits!

        checkNeighbour(myStringXY.getEnemyGameBoard().hitList);  //Secure spots all around
        /*boolean transversal = false; //find direction of boat
        int xFirstHit = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
        int yFirstHit = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
        if (x == xFirstHit) { //If x constant, boat is transversal, otherwise horizontal
            transversal = true;
        }
        secure(x, y, transversal);
        Collections.sort(myStringXY.getEnemyGameBoard().hitList);*/



        myStringXY.getEnemyGameBoard().hitList.clear();   // clear hitList

        //Pick next random shot from remainingXYspots
        x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));
        y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));
        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);

        String text = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());  // remove xyValue from remainingXYspots

        return text;
    }

    private void checkNeighbour(List<String> hitList) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < hitList.size(); i++) {

            x = myParceValue.stringToXint(hitList.get(i));
            y = myParceValue.stringToYint(hitList.get(i));

            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].setWasHit(true);
                myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }

        }

    }

    private void secure(int p, int q, boolean topdown) {

        System.out.println("Securing..............");


        if (!topdown) { //Horizontal boat
            for (int i = 1; i < 4; i++) {

                if (p == 0 && q == 0) { //Upper left corner

                    myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add
                            (myStringXY.getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());

                } else if (p == 9 && q == 0) { //Upper right corner
                    myStringXY.getRemainingEnemyPositions()[p - i + 1][q].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());

                } else if (p == 9 && q == 9) { //Lower right corner
                    myStringXY.getRemainingEnemyPositions()[p - i + 1][q - 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - i + 1][q - 1].getXyValue());

                } else if (p == 0 && q == 9) { //Lower left corner
                    myStringXY.getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());

                } else if (p == 0) { //Left column, boat horizontal

                    if (q != 0) { //Secure spots above
                        myStringXY.getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());
                    }
                    if (q != 9) { //Secure spots below
                        myStringXY.getRemainingEnemyPositions()[p + i - 1][q + 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());
                    }

                } else if (p == 9) { //Right column, boat horizontal
                    if (q != 0) { //Secure spots above
                        myStringXY.getRemainingEnemyPositions()[p - i - 1][q - 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - i - 1][q - 1].getXyValue());
                    }
                    if (q != 9) { //Secure spots below
                        myStringXY.getRemainingEnemyPositions()[p - i + 1][q + 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());
                    }

                } else if (q == 0) { //Top row, boat horizontal, secure below
                    myStringXY.getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                    //break;
                    //case 'B':

                } else if (q == 9) { //Bottom row, secure above
                    myStringXY.getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                    //break;
                    //case 'C':

                } else {   //Any other position on GameBoard, secure below and above
                    myStringXY.getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    myStringXY.getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                }
            }
        }

        if (topdown) { //Vertical boat
            for (int i = 1; i < 4; i++) {
                if (p == 0 && q == 0) { //Upper left corner, secure on right
                    myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());

                } else if (p == 9 && q == 0) {//Upper right corner, secure on left
                    myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());

                } else if (p == 9 && q == 9) { //Lower right corner, secure on left
                    myStringXY.getRemainingEnemyPositions()[p - 1][q + 1 - i].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q + 1 - i].getXyValue());

                } else if (p == 0 && q == 9) { //Lower left corner, secure on right
                    myStringXY.getRemainingEnemyPositions()[p + 1][q + 1 - i].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q + 1 - i].getXyValue());

                } else if (p == 0) { //Left column, secure right
                    myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());

                } else if (p == 9) { //Right column, secure left
                    myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());

                } else if (q == 0) {//Top row, boat transversal
                    if (p != 0) { //secure right
                        myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());
                    }

                } else if (q == 9) {//Bottom row, boat transversal
                    if (p != 0) { //secure right
                        myStringXY.getRemainingEnemyPositions()[p + 1][q - i + 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q - i + 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        myStringXY.getRemainingEnemyPositions()[p - 1][q - i + 1].setWasHit(true);
                        myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q - i + 1].getXyValue());
                    }

                } else { //Any other position on GameBoard, secure left and right
                    myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                    myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);

                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());
                    myStringXY.getEnemyGameBoard().getGuardedSpots().add(myStringXY.getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());
                }
            }
        }
        myStringXY.getEnemyGameBoard().getRemainingXYspots().removeAll(myStringXY.getEnemyGameBoard().guardedSpots);
        myStringXY.getEnemyGameBoard().getGuardedSpots().clear();
        System.out.println("Secure, proceed");

    }


}

