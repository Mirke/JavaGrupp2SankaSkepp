package com.grupp2.sankaskepp.Remaining;

public class HitHorizontal extends PluralHits{

    /*public static String horizontal(int p, int q) {


        ComputerLogic nextInRow = new ComputerLogic();

        int p0 = nextInRow.getMyParceValue().stringToXint(nextInRow.getMyStringXY().getEnemyGameBoard().hitList.get(0));
        int p1 = nextInRow.getMyParceValue().stringToXint(nextInRow.getMyStringXY().getEnemyGameBoard().hitList.get(nextInRow.getMyStringXY().getEnemyGameBoard().hitList.size() - 1));



        //Kolla åt vilket håll föregående träff var - om åt höger, stega vänster resp om åt vänster stega höger. Hur hålla koll på
        //ändpunkter om första träffen var i mitten - stega åt höger till kanten eller  miss = was hit, därefter stega åt vänster. Räcker det som logik?
        if (p1 > p0) {
            if (p1 != 9 && !nextInRow.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p1 + 1][q].wasHit) {
                p = p1 + 1;
            } else if (p1 <= 8 && nextInRow.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p1 + 1][q].wasHit) {
                p = p0 - 1;
            }
        }

            if (p0 > p1) {

        if (p1 != 0 && !nextInRow.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p0 - 1][q].wasHit){
                p = p0 - 1;
        } else if (p1 !=0 && nextInRow.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p1 - 1][q].wasHit) {
            p = p1 + 1;
            }
        }
        return nextInRow.getMyStringXY().getEnemyGameBoard().getRemainingEnemyPositions()[p][q].getXyValue();


    }*/
}
