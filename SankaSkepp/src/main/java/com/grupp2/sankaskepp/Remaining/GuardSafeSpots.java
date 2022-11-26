package com.grupp2.sankaskepp.Remaining;

public class GuardSafeSpots  {

    // Karin go wild
    //Constructor
    public GuardSafeSpots() {}


    public void secure(int p, int q, boolean topdown) {

       MyStringXY protect = new MyStringXY();

        System.out.println("Securing..............");
        if (!topdown) { //Horizontal boat
            for (int i = 1; i < 4; i++) {
                //switch (getActual()) {
                  //  case 'N':
                        if (p == 0 && q == 0) { //Upper left corner
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());

                        //break;
                    //case 'E':
                      } else if (p == 9 && q == 0) { //Upper right corner
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());

                            //break;
                            //case 'S':
                         } else if (p == 9 && q == 9) { //Lower right corner
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q - 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q - 1].getXyValue());

                            //break;
                            //case 'W':
                        } else if (p == 0 && q == 9) { //Lower left corner
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());

                            //break;
                            //case 'L':
                         } else if (p == 0) { //Left column, boat horizontal

                        if (q != 0) { //Secure spots above
                            protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                            protect.getEnemyGameBoard().guardedSpots.add
                                    (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());
                        }
                        if (q != 9) { //Secure spots below
                            protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q + 1].setWasHit(true);
                            protect.getEnemyGameBoard().guardedSpots.add
                                    (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());
                        }

                            //break;
                            //case 'R':
                        } else if (p == 9) { //Right column, boat horizontal
                        if (q != 0) { //Secure spots above
                            protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i - 1][q - 1].setWasHit(true);
                            protect.getEnemyGameBoard().guardedSpots.add
                                    (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i - 1][q - 1].getXyValue());
                        }
                        if (q != 9) { //Secure spots below
                            protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q + 1].setWasHit(true);
                            protect.getEnemyGameBoard().guardedSpots.add
                                    (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());
                        }

                            //break;
                            //case 'T':

                        } else if (q == 0) { //Top row, boat horizontal, secure below
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                            //break;
                            //case 'B':

                   } else if (q == 9) { //Bottom row, secure above
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                            //break;
                            //case 'C':

                } else {   //Any other position on GameBoard, secure below and above
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                }
            }
        }

        if (topdown) { //Vertical boat
            for (int i = 1; i < 4; i++) {
                //switch (getActual()) {
                //case 'N':
                 if (p == 0 && q == 0) { //Upper left corner, secure on right
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());
                     //break;
                     //case 'E':
                } else if (p == 9 && q == 0) {//Upper right corner, secure on left
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());
                     //break;
                     //case 'S':
                } else if (p == 9 && q == 9) { //Lower right corner, secure on left
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + 1 - i].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + 1 - i].getXyValue());
                     // break;
                     // case 'W':
                } else if (p == 0 && q == 9) { //Lower left corner, secure on right
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + 1 - i].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + 1 - i].getXyValue());
                     // break;

                     // case 'L':
                } else if (p == 0) { //Left column, secure right
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());
                     //break;
                     // case 'R':
                } else if (p == 9) { //Right column, secure left
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());

                     //break;
                     // case 'T':
                } else if (q == 0) {//Top row, boat transversal
                    if (p != 0) { //secure right
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());
                    }
                     //break;
                     //case 'B':
                } else if (q == 9) {//Bottom row, boat transversal
                    if (p != 0) { //secure right
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q - i + 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q - i + 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q - i + 1].setWasHit(true);
                        protect.getEnemyGameBoard().guardedSpots.add
                                (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q - i + 1].getXyValue());
                    }


                     //break;
                     //case 'C':
                } else { //Any other position on GameBoard, secure left and right
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                    protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);

                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());
                    protect.getEnemyGameBoard().guardedSpots.add
                            (protect.getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());
                }
            }
        }
        protect.getEnemyGameBoard().remainingXYspots.removeAll(protect.getEnemyGameBoard().guardedSpots);
        protect.getEnemyGameBoard().guardedSpots.clear();
        System.out.println("Secure, proceed");
        //return "Guarding completed";

    }
}










