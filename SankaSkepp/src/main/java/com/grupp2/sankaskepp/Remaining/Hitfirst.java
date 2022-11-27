package com.grupp2.sankaskepp.Remaining;

public class Hitfirst extends PluralHits { //Karin

    /*public Hitfirst() {
        super();
    }


        public String secondShot( int p, int q){


            ComputerLogic newShot = new ComputerLogic();


            switch (getActual()) {
                case 'N': {  //Check upper left corner
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q + 1].wasHit) {
                        q = q + 1;
                    } else p = p + 1;
                }
                case 'W': { // Check lower left corner
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    } else p = p + 1;
                }

                case 'S': { //Check lower right corner
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    } else p = p - 1;
                }

                case 'E': { //Check lower left corner
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    } else p = p + 1;
                }

                //Check if border position along x = 0

                case 'L': {  //Check Left column x = 0
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q + 1].wasHit) {
                        q = q + 1;
                    }
                    else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q].wasHit) {
                        p = p + 1;
                    } else q = q - 1;
                }

                case 'T': { // Check Top row
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q].wasHit) {
                        p = p + 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q + 1].wasHit) {
                        q = q + 1;
                    } else p = p - 1;
                }

                case 'B': { //Check Bottom row
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q].wasHit) {
                        p = p + 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    } else p = p - 1;
                }

                case 'R': { //Check Right column
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q].wasHit) {
                        p = p - 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    } else p = p + 1;
                }
                case 'C': {
                    if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p + 1][q].wasHit) {
                        p = p + 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p - 1][q].wasHit) {
                        p = p - 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q + 1].wasHit) {
                        q = q + 1;
                    } else if (!newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q - 1].wasHit) {
                        q = q - 1;
                    }

                }

            }
            return newShot.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q].xyValue;
        }*/



}
