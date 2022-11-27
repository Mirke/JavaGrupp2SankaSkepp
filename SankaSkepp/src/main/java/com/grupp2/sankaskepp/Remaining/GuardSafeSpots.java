package com.grupp2.sankaskepp.Remaining;

public class GuardSafeSpots  { //Inlagt i ComputerLogic för att få spelet att fungera, denna klass används ej

    // Karin go wild
    //Constructor
    public GuardSafeSpots() {} //Används ej
/*

    public static void secure(int p, int q, boolean topdown) {

        //EnemyGameBoard protect = new EnemyGameBoard();

       //MyStringXY protect = new MyStringXY();

        System.out.println("Securing..............");


        if (!topdown) { //Horizontal boat
            for (int i = 1; i < 4; i++) {
                //switch (getActual()) {
                //  case 'N':
                if (p == 0 && q == 0) { //Upper left corner

               myStringXY.getEnemyGameBoard().getRemainingEnemyPositions()[x][y].getXyValue()
                    protect.getRemainingEnemyPositions()[p + i - 1][q].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add
                            (protect.getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());

                    //break;
                    //case 'E':
                    } else if (p == 9 && q == 0) { //Upper right corner
                        protect.getRemainingEnemyPositions()[p - i + 1][q].setWasHit(true);
                        protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());


                            //break;
                            //case 'S':
                         } else if (p == 9 && q == 9) { //Lower right corner
                        protect.getRemainingEnemyPositions()[p - i + 1][q - 1].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - i + 1][q - 1].getXyValue());

                            //break;
                            //case 'W':
                        } else if (p == 0 && q == 9) { //Lower left corner
                        protect.getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());

                            //break;
                            //case 'L':
                         } else if (p == 0) { //Left column, boat horizontal

                        if (q != 0) { //Secure spots above
                            protect.getRemainingEnemyPositions()[p + i - 1][q - 1].setWasHit(true);
                            protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 1][q - 1].getXyValue());
                        }
                        if (q != 9) { //Secure spots below
                            protect.getRemainingEnemyPositions()[p + i - 1][q + 1].setWasHit(true);
                            protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 1][q + 1].getXyValue());
                        }

                            //break;
                            //case 'R':
                        } else if (p == 9) { //Right column, boat horizontal
                        if (q != 0) { //Secure spots above
                            protect.getRemainingEnemyPositions()[p - i - 1][q - 1].setWasHit(true);
                            protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - i - 1][q - 1].getXyValue());
                        }
                        if (q != 9) { //Secure spots below
                            protect.getRemainingEnemyPositions()[p - i + 1][q + 1].setWasHit(true);
                            protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - i + 1][q + 1].getXyValue());
                        }

                            //break;
                            //case 'T':

                        } else if (q == 0) { //Top row, boat horizontal, secure below
                        protect.getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                            //break;
                            //case 'B':

                   } else if (q == 9) { //Bottom row, secure above
                    protect.getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                            //break;
                            //case 'C':

                } else {   //Any other position on GameBoard, secure below and above
                   protect.getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                    protect.getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                    protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                }
            }
        }

        if (topdown) { //Vertical boat
            for (int i = 1; i < 4; i++) {
                //switch (getActual()) {
                //case 'N':
                 if (p == 0 && q == 0) { //Upper left corner, secure on right
                    protect.getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());
                     //break;
                     //case 'E':
                } else if (p == 9 && q == 0) {//Upper right corner, secure on left
                    protect.getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());
                     //break;
                     //case 'S':
                } else if (p == 9 && q == 9) { //Lower right corner, secure on left
                    protect.getRemainingEnemyPositions()[p - 1][q + 1 - i].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q + 1 - i].getXyValue());
                     // break;
                     // case 'W':
                } else if (p == 0 && q == 9) { //Lower left corner, secure on right
                    protect.getRemainingEnemyPositions()[p + 1][q + 1 - i].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q + 1 - i].getXyValue());
                     // break;

                     // case 'L':
                } else if (p == 0) { //Left column, secure right
                    protect.getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());
                     //break;
                     // case 'R':
                } else if (p == 9) { //Right column, secure left
                    protect.getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());

                     //break;
                     // case 'T':
                } else if (q == 0) {//Top row, boat transversal
                    if (p != 0) { //secure right
                        protect.getRemainingEnemyPositions()[p + 1][q + i - 1].setWasHit(true);
                        protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q + i - 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        protect.getRemainingEnemyPositions()[p - 1][q + i - 1].setWasHit(true);
                        protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q + i - 1].getXyValue());
                    }
                     //break;
                     //case 'B':
                } else if (q == 9) {//Bottom row, boat transversal
                    if (p != 0) { //secure right
                        protect.getRemainingEnemyPositions()[p + 1][q - i + 1].setWasHit(true);
                        protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q - i + 1].getXyValue());
                    }
                    if (p != 9) { //secure left
                        protect.getRemainingEnemyPositions()[p - 1][q - i + 1].setWasHit(true);
                        protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q - i + 1].getXyValue());
                    }


                     //break;
                     //case 'C':
                } else { //Any other position on GameBoard, secure left and right
                    protect.getRemainingEnemyPositions()[p + 1][q + i - 2].setWasHit(true);
                    protect.getRemainingEnemyPositions()[p - 1][q + i - 2].setWasHit(true);

                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p + 1][q + i - 2].getXyValue());
                     protect.getEnemyGameBoard().getGuardedSpots().add(protect.getRemainingEnemyPositions()[p - 1][q + i - 2].getXyValue());
                }
            }
        }
        protect.getEnemyGameBoard().getRemainingXYspots().removeAll(protect.getGuardedSpots());
        protect.getGuardedSpots().clear();
        System.out.println("Secure, proceed");
        //return "Guarding completed";*/


            }











