package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;

import java.util.Collections;
import java.util.List;

/*
    Karins MainRemain har flyttats hit
 */
public class ComputerLogic extends PluralHits {

    // Attributes
    private String enemyShot = "";    //textsträngen från motspelaren, kommer bli indexOutOfBounds eftersom den är tom

    private MyStringXY myStringXY = new MyStringXY();

    private MyParceValue myParceValue = new MyParceValue();

    GuardSafeSpots guardSafeSpots = new GuardSafeSpots();

    Hitfirst hitfirst = new Hitfirst();

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

        int p = x;
        int q = y;

        // Check hitList>1 guard positions around >1 hit

        if (myStringXY.getEnemyGameBoard().hitList.size() > 1) {

            boolean transversal = false;
            int x0 = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
            int y0 = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
            if (x == x0) {
                transversal = true;
            } //If x constant, boat is transversal

            guardSafeSpots.safeSpots(x, y, transversal);

            //hitHorizontal.horizontal(x, y);


            if (!transversal) {
                //Kolla åt vilket håll föregående träff var - om åt höger, stega vänster resp om åt vänster stega höger. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega åt höger till kanten eller  miss = was hit, därefter stega åt vänster. Räcker det som logik?
                if (x > x0) { //We are stepping to the right, continue until stopped

                    if (x <= 8 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x + 1;
                    } else {
                        x = x0 - 1;
                    }
                }

                if (x0 > x) {//second hit to the left of first hit

                    if (x != 0 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x = x - 1;

                    } else {
                        x = x0 + 1;
                    }
                }


            } else {
                //Kolla åt vilket håll föregående träff var - om neråt, stega neråt resp om uppåt stega uppåt. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega uppåt till kanten eller  miss = was hit, därefter stega neråt. Räcker det som logik?
                if (y > y0) { //We are stepping down, continue until stopped
                    if (y != 8 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else {
                        y = y0 - 1;
                    }
                }
                if (y0 > y) {//second hit to the left of first hit

                    if (y != 0 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else {
                        y = y0 + 1;
                    }
                }
            }

        } else {
            System.out.println("____endast en träff_____");
            //String pq = hitfirst.secondShot(x, y); //Skapa XYValue för nästa skott

            switch (getActual()) {
                case 'N': {  //Check upper left corner
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else x = x + 1;
                }
                case 'W': { // Check lower left corner
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else x = x + 1;
                }

                case 'S': { //Check lower right corner
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else x = x - 1;
                }

                case 'E': { //Check lower left corner
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else x = x + 1;
                }

                //Check if border position along x = 0

                case 'L': {  //Check Left column x = 0
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x + 1;
                    } else y = y - 1;
                }

                case 'T': { // Check Top row
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x + 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else x = x - 1;
                }

                case 'B': { //Check Bottom row
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x + 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else x = x - 1;
                }

                case 'R': { //Check Right column
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x = x - 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    } else x = x + 1;
                }
                case 'C': {
                    if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x + 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x = x - 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else if (!myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y - 1;
                    }
                }

            }
        }

        if (x == p && y == q) {
            myStringXY.getEnemyGameBoard().hitList.clear();
            nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));

            x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));
            y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));


            myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        } else nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
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

        //We need logic in "hasShip" to reduce number of possibilities.
        //With two "hasShip" in a row we can avoid unnecessary shots.


        if (myStringXY.getEnemyGameBoard().hitList.size() >= 1) {
            int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));
            int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 1));

            boolean transversal = false;
            int x0 = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(0));
            int x1 = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().hitList.get(myStringXY.getEnemyGameBoard().hitList.size() - 2));
            int y0 = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().hitList.get(0));
            if (x1 == x) { //If x constant, boat is transversal
                transversal = true;
            }

            //GuardSafeSpots.safeSpots(x, y, transversal);

            //HitHorizontal.horizontal(x, y); - in separate method?
            if (!transversal) {
                //Kolla åt vilket håll föregående träff var - om åt höger, stega vänster resp om åt vänster stega höger. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega åt höger till kanten eller  miss = was hit, därefter stega åt vänster. Räcker det som logik?
                if (x > x0) { //We are stepping to the right, continue until stopped
                    if (x != 8 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x++;
                    } else if (myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit) {
                        x = x0 - 1;
                    }
                }

                if (x0 > x1) {//second hit to the left of first hit

                    if (x != 0 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x0 - 1][y].wasHit) {
                        x = x0 - 1;
                    } else if (myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit) {
                        x = x + 1;

                    }
                }
            }

            if (transversal) {
                //Kolla åt vilket håll föregående träff var - om neråt, stega neråt resp om uppåt stega uppåt. Hur hålla koll på
                //ändpunkter om första träffen var i mitten - stega uppåt till kanten eller  miss = was hit, därefter stega neråt. Räcker det som logik?
                if (y > y0) { //We are stepping down, continue until stopped
                    if (y != 8 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y + 1;
                    } else if (myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit) {
                        y = y0 - 1;
                    }
                }

                if (y0 > y) {//second hit to the left of first hit

                    if (y != 0 && !myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y0 - 1].wasHit) {
                        y = y0 - 1;
                    } else if (myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit) {
                        y = y + 1;
                    }
                }
            }
            nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // add new xyValue to hitList
            myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()); // remove xyValue

        } else {
            //System.out.println("inne i else");
            myStringXY.getEnemyGameBoard().hitList.clear();
            nextXY = " shot ".concat(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));

            int x = myParceValue.stringToXint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));
            int y = myParceValue.stringToYint(myStringXY.getEnemyGameBoard().getRemainingXYspots().get(0));


            myStringXY.getEnemyGameBoard().hitList.add(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringXY.getEnemyGameBoard().remainingXYspots.remove(myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        }
        System.out.println("returnerar" + nextXY);
        return nextXY;
    }

    public String sForSink(ControlOfInput controlOfInput) {

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

