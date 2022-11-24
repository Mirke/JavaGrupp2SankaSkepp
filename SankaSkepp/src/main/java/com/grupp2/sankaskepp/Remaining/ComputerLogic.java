package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.MyParceValue;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;

import java.util.Collections;
import java.util.List;

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
        Collections.shuffle(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots());  // return Random xyValue from remainingXYspots

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
        Collections.shuffle(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots());  // return Random xyValue from remainingXYspots

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

    public String hForHit() {
        // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
        // find position next to XyValue in hitList, will be  next shot
        /*
        String text =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().remainingXYspots.get(0));
        myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
        */
        boolean horisontal = true;
        boolean vertical = true;

        int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
        int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));;
        String nextXY = "";

        //Check which way we want to check depending on earlier values
        if(myStringCoordinates.getEnemyGameBoard().hitList.size() >= 2){
            String first = myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);
            String second =  myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 2);

            int x1 = myParceValue.stringToXint(first);
            int y1 = myParceValue.stringToYint(first);
            int x2 = myParceValue.stringToXint(second);
            int y2 = myParceValue.stringToYint(second);

            if(x1 == x2){
                horisontal = false;
            }
            if(y1 == y2){
                vertical = false;
            }


        }

        boolean works = false;
        if(y != 0 && y != 9 && x != 0 && x != 9){
            if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                x++;
                works = true;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                x--;
                works = true;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                y++;
                works = true;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                y--;
                works = true;
            }

        }else{
            if (x == 0 && y == 0) {
                //Check upper left corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                    y++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                    works = true;
                }

            }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                        works = true;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                        works = true;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                        works = true;
                    }
                } else if (y == 0) { // Check positions along y = 0
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                    x--;
                    works = true;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                    y++;
                    works = true;
                }

            } else if (y == 9) { //Check border along y = 9
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                    works = true;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                    x--;
                    works = true;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                    y--;
                    works = true;
                }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                        works = true;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                        works = true;
                    }
            }
        }


        if(!works){
            x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));
            y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));

            if (x == 0 && y == 0) {
                //Check upper left corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                    y++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                }

            }else if (y == 9 && x == 0) { // Check lower left corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                    y--;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                    x++;
                }
                //Check if border position along x = 0
            } else if (x == 9 && y == 0) {

                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                    y++;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                    x--;
                }

            } else if (y == 9 && x == 9) { //Check lower right corner
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                    y--;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                    x--;
                }
            }else if (y == 0) { // Check positions along y = 0
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                    x--;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                    y++;
                }

            }else if (y == 9) { //Check border along y = 9
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                    x++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                    x--;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                    y--;
                }
            }else if(x == 0){
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                    y++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                    y--;
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                    x++;
                }
            }else if (x == 9) { //Check border along y = 9
                if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                    y++;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                    y--;
                } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                    x--;
                }
            } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                x++;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                x--;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                y++;
            }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                y--;
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

    public String mForMiss() {
        String nextXY = "";
        boolean horisontal = true;
        boolean vertical = true;
        myStringCoordinates.getEnemyGameBoard().hitList.remove(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);

        if(myStringCoordinates.getEnemyGameBoard().hitList.size() >= 2){
            String first = myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1);
            String second =  myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 2);

            int x1 = myParceValue.stringToXint(first);
            int y1 = myParceValue.stringToYint(first);
            int x2 = myParceValue.stringToXint(second);
            int y2 = myParceValue.stringToYint(second);

            if(x1 == x2){
                horisontal = false;
            }
            if(y1 == y2){
                vertical = false;
            }
        }

        boolean works = false;
        if (myStringCoordinates.getEnemyGameBoard().hitList.size() >= 1) {
            int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));
            int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(myStringCoordinates.getEnemyGameBoard().hitList.size() - 1));

                if (x == 0 && y == 0) {
                    //Check upper left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                        works = true;
                    }

                }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                        works = true;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                        works = true;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                        works = true;
                    }
                }else if (y == 0) { // Check positions along y = 0
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                        y++;
                        works = true;
                    }

                }else if (y == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                        y--;
                        works = true;
                    }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                        works = true;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                        works = true;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                        works = true;
                    }
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                    x++;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                    x--;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                    y++;
                    works = true;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                    y--;
                    works = true;
                }

                if(!works){
                x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));
                y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().hitList.get(0));

                if (x == 0 && y == 0) {
                    //Check upper left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                    }

                }else if (y == 9 && x == 0) { // Check lower left corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                    }
                    //Check if border position along x = 0
                } else if (x == 9 && y == 0) {

                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                    }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                    }

                } else if (y == 9 && x == 9) { //Check lower right corner
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                        x--;
                    }
                }else if (y == 0) { // Check positions along y = 0
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                        y++;
                    }

                }else if (y == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal) {
                        x++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
                        y--;
                    }
                }else if(x == 0){
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                    } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                        x++;
                    }
                }else if (x == 9) { //Check border along y = 9
                    if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical) {
                        y++;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical) {
                        y--;
                    } else if (!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal) {
                        x--;
                    }
                } else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].wasHit && horisontal){
                    x++;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].wasHit && horisontal){
                    x--;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].wasHit && vertical){
                    y++;
                }else if(!myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].wasHit && vertical){
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

        }else{
            myStringCoordinates.getEnemyGameBoard().hitList.clear();
            nextXY =  " shot ".concat(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

            int x = myParceValue.stringToXint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));
            int y = myParceValue.stringToYint(myStringCoordinates.getEnemyGameBoard().getRemainingXYspots().get(0));

            myStringCoordinates.getEnemyGameBoard().hitList.add(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
            myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].setWasHit(true);
            myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue());
        }
        return nextXY;
    }

    public String sForSink() {
        // EnemyBoats.length tells which boat was sunk
        // logic to clear safe points around boat - add positions to guardedSpots[]
        // remove boat from ListEnemyBoats[]
        // remove guardedSpots from remainingXYspots
        checkNeighbour(myStringCoordinates.getEnemyGameBoard().getHitList());
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

    /*tobias*/
    private void checkNeighbour(List<String> hitList) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < hitList.size(); i++) {

            x = myParceValue.stringToXint(hitList.get(i));
            y = myParceValue.stringToYint(hitList.get(i));

            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y - 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x][y + 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y - 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y - 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y - 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x + 1][y + 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x + 1][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }
            try {
                myStringCoordinates.getEnemyGameBoard().getRemainingEnemyPositions()[x - 1][y + 1].setWasHit(true);
                myStringCoordinates.getEnemyGameBoard().remainingXYspots.remove(myStringCoordinates.getEnemyGameBoard()
                        .getRemainingEnemyPositions()[x - 1][y + 1].getXyValue());  // remove xyValue from remainingXYspots

            } catch (IndexOutOfBoundsException e) {
            }

        }
    }
    /*tobias*/

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
