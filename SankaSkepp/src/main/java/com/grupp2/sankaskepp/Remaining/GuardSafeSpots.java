package com.grupp2.sankaskepp.Remaining;

public class GuardSafeSpots extends PluralHits {

    // go wild
    //Constructor
    //public GuardSafeSpots() {}


    public void safeSpots(int p, int q, boolean topdown) {

        ComputerLogic protect = new ComputerLogic();


        if (!topdown) { //Horizontal boat
            for (int i = 1; i < 4; i++) {
                switch (getActual()) {
                    case 'N':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q].getXyValue());
                        break;
                    case 'E':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][1].getXyValue());
                        break;
                    case 'S':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q - 1].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1 - i][q - 1].getXyValue());
                        break;
                    case 'W':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q - 1].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q - 1].getXyValue());
                        break;
                    case 'L':
                        if (q != 0) {
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q - 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q - 1].getXyValue());
                        }
                        if (q != 9) {
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q + 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[i - 1][q + 1].getXyValue());
                        }
                        break;
                    case 'R':
                        if (q != 0) {
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1][q - 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1 - i][q - 1].getXyValue());
                        }
                        if (q != 9) {
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1 - i][q + 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - i + 1 - i][q + 1].getXyValue());
                        }
                        break;
                    case 'T':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                        break;
                    case 'B':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                        break;
                    case 'C':
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                        protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].setWasHit(true);

                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                        protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q + 1].getXyValue());
                }
            }
        }

            if (topdown) { //Vertical boat
                for (int i = 1; i < 4; i++) {
                    switch (getActual()) {
                        case 'N':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][i - 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][i - 1].getXyValue());
                            break;
                        case 'E':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][i - 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[8][i - 1].getXyValue());
                            break;
                        case 'S':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][10 - i].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[8][10 - i].getXyValue());
                            break;
                        case 'W':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][10 - i].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][10 - i].getXyValue());
                            break;
                        case 'L':
                            if (q != 0) {
                                protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][q - i].setWasHit(true);
                                protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                        (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][q - i].getXyValue());
                            }
                            if (q != 9) {
                                protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][q + i].setWasHit(true);
                                protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                        (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[1][q + i].getXyValue());
                            }

                            break;
                        case 'R':
                            if (q != 0) {
                                protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q - 1].setWasHit(true);
                                protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                        (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q - 1].getXyValue());
                            }
                            if (q != 9) {
                                protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + 1].setWasHit(true);
                                protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                        (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q + 1].getXyValue());}
                            break;
                        case 'T':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][1].getXyValue());
                            break;
                        case 'B':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + i - 2][q - 1].getXyValue());
                            break;
                        case 'C':
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 2][q + i].setWasHit(true);
                            protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 2][q - i].setWasHit(true);

                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 2][q - 1].getXyValue());
                            protect.getMyStringXY().getEnemyGameBoard().guardedSpots.add
                                    (protect.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 2][q + 1].getXyValue());
                    }
                }
            }
                protect.getMyStringXY().getEnemyGameBoard().remainingXYspots.removeAll(protect.getMyStringXY().getEnemyGameBoard().guardedSpots);
                //return "Guarding completed";

    }
}










