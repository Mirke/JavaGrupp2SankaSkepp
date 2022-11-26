package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;

import java.util.Collections;
import java.util.List;

public class ComputerLogic extends GuardSafeSpots {

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
    }

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

            guardSafeSpots.secure(x, y, transversal); //Här hänger sig programmet just nu! Vad är fel i anropet av metoden?


            //hitHorizontal.horizontal(x, y);


            if (!transversal) { //Horizontal boat
                    if (x > xFirstHit) { //keep going right until stopped, else right
                    if (x == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        xNext = xFirstHit - 1;
                    } else  {
                        xNext = x + 1;
                    }
                }

                if (x < xFirstHit) { //keep going left until stopped, else right
                    if (x == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        xNext = xFirstHit + 1;
                    } else  {
                        xNext = x - 1;
                    }
                }


            } else { //transversal boat
                    if (y > yFirstHit) { //keep going down until stopped, else up
                    if (y == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        yNext = yFirstHit - 1;
                    } else  {
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
            //String pq = hitfirst.secondShot(x, y); //Skapa XYValue för nästa skott

            //switch (getActual()) {
            //case 'N':

            if (x == 0 && y == 0) {  //Check upper left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else xNext = x + 1;

            }
            // case 'W':

            else if (x == 0 && y == 9) { // Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x + 1;

            }

            //case 'S':

            else if (x == 9 && y == 9) { //Check lower right corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x - 1;
            }

            //case 'E':

            else if (x == 9 && y == 0) { //Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    xNext = x - 1;
                } else yNext = y + 1;


            }

            //Check if border position along x = 0

            //case 'L':

            else if (x == 0) {  //Check Left column x = 0
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else yNext = y - 1;

            }

            //case 'T':

            else if (y == 0) { // Check Top row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    yNext = y + 1;
                } else xNext = x - 1;

            }

            //case 'B':

            else if (y == 9) { //Check Bottom row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    xNext = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else xNext = x - 1;

            }

            // case 'R':

            else if (x == 9) { //Check Right column
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    xNext = x - 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    yNext = y - 1;
                } else yNext = y + 1;

            }
            //case 'C':
            else { //if (p >= 1 && p <= 8 && q <= 8 && q >= 1)
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

        if (xNext != x){
            x = xNext;
        }
        if (yNext != y){
            y = yNext;
        }

        nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());

        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // add new xyValue to hitList
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // remove xyValue from remainingXYspots

        return nextXY;


    }


    public String mForMiss(ControlOfInput controlOfInput) {
        System.out.println("inne i mformiss");
        String nextXY = "";

        myStringXY.getEnemyGameBoard().hitList.remove(myStringXY.getEnemyGameBoard().hitList.size() - 1);
        //Removes last shot (miss), check around latest hit for new possible shot.
        int x = 0;
        int y = 0;
        int newX = x;
        int newY = y;



        if (myStringXY.getEnemyGameBoard().hitList.size() == 0) {
            myStringXY.getEnemyGameBoard().hitList.clear();

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().remainingXYspots.get(0));
            newX = x;
            newY = y;


        } else if (myStringXY.getEnemyGameBoard().hitList.size() == 1) {   //hitList contains only one value, no direction yet

            System.out.println(" inne i miss____endast en träff_____");
            //String pq = hitfirst.secondShot(x, y); //Skapa XYValue för nästa skott

            //switch (getActual()) {
            //case 'N':
            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            newX = x;
            newY = y;


            if (x == 0 && y == 0) {  //Check upper left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else newX = x + 1;
            }
            // case 'W':

            else if (x == 0 && y == 9) { // Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x + 1;
            }

            //case 'S':

            else if (x == 9 && y == 9) { //Check lower right corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x - 1;
            }

            //case 'E':

            else if (x == 9 && y == 0) { //Check lower left corner
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    newX = x - 1;
                } else newY = y + 1;
            }

            //Check if border position along x = 0

            //case 'L':

            else if (x == 0) {  //Check Left column x = 0
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else newY = y - 1;
            }

            //case 'T':

            else if (y == 0) { // Check Top row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                    newY = y + 1;
                } else newX = x - 1;
            }

            //case 'B':

            else if (y == 9) { //Check Bottom row
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                    newX = x + 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newX = x - 1;
            }

            // case 'R':

            else if (x == 9) { //Check Right column
                if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                    newX = x - 1;
                } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                    newY = y - 1;
                } else newY = y + 1;
            }
            //case 'C':
            else { //if (p >= 1 && p <= 8 && q <= 8 && q >= 1)
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

            System.out.println("inne i miss, >2 träffar");

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            newX = x;
            newY = y;

            boolean transversal = false;
            int x0 = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
            int y0 = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
            if (x == x0) {
                transversal = true;
            } //If x constant, boat is transversal


            if (!transversal) {
                //Kolla åt vilket håll föregående träff var - om åt höger, stega vänster resp om åt vänster stega höger. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega åt höger till kanten eller  miss = was hit, därefter stega åt vänster. Räcker det som logik?
                if (x > x0) { //We are stepping to the right, continue until stopped
                    if (x == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        newX = x0 - 1;
                    } else  {
                        newX = x + 1;
                    }
                }

                if (x < x0) {//second hit to the left of first hit, x is lowest value
                    if (x == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        newX = x0 + 1;
                    } else  {
                        newX = x - 1;
                    }
                }


            } else { //transversal boat
                //Kolla åt vilket håll föregående träff var - om neråt, stega neråt resp om uppåt stega uppåt. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega uppåt till kanten eller  miss = was hit, därefter stega neråt. Räcker det som logik?
                if (y > y0) { //We are stepping down, continue until stopped
                    if (y == 9 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        newY = y0 - 1;
                    } else  {
                        newY = y + 1;
                    }
                }
                if (y < y0) {//second hit to the left of first hit

                    if (y == 0 || myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        newY = y0 + 1;
                    } else {
                        newY = y - 1;
                    }
                }
            }

        }

        if (newX != x){
            x = newX;
        }
        if (newY != y){
            y = newY;
        }
        nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
        myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // add new xyValue to hitList
        myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // remove xyValue from remainingXYspots

        //System.out.println("returnerar" + nextXY);


        return nextXY;
    }


    public String sForSink(ControlOfInput controlOfInput) { //Tobias

        int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));

        String nextXY = "";
        myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setHasBoat(true); //Keep track on succesful hits!
        //myStringCoordinates.getEnemyBoatList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][x].getXyValue());//Addera till listEnemyBoats[]


        myStringXY.getEnemyGameBoard().hitList.clear();   // clear hitList
        //Collections.shuffle(myStringCoordinates.getRemainingXYspots());  // return Random xyValue from remainingXYspots
        //Unnecessary shuffle random list


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

}

