package com.grupp2.sankaskepp.Remaining;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainRemain {
    public static void main(String[] args) {


        EnemyGameBoard enemyGameBoard = new EnemyGameBoard();

        YourGameBoard yourGameBoard = new YourGameBoard();

        XYposition xyPosition = new XYposition();

        //Make List of all positions in EnemyGameBoard - Karin

        List<String> remainingXYspots = (List<String>) enemyGameBoard.getRemainingXYspots();

        XYposition[][] remainingEnemyPositions = enemyGameBoard.getRemainingEnemyPositions();


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                remainingEnemyPositions[i][j] = new XYposition
                        (enemyGameBoard.getxValue()[i], enemyGameBoard.getyValue()[j], false, false);

                yourGameBoard[i][j] = new XYposition
                        (yourGameBoard.getxValue()[i], yourGameBoard.getyValue()[j], false, false);

                remainingXYspots.add(remainingEnemyPositions[i][j].getXyValue());
            }

        }

        //Logik för nästa skott, beroende på var föregående skott träffade

        public String enemyShot;    //textsträngen från motspelaren

        Boat EnemyBoats[] = new Boat[];

        //while EnemyBoats[] == !0; - formulera logiken mer korrekt

        if (Character.toString(enemyShot.charAt(0)).equals("i")) {
            Collections.shuffle(remainingXYspots);  // return Random xyValue from remainingXYspots
            enemyGameBoard.hitList.add(enemyGameBoard.remainingXYspots.get(0));  // add new XyValue to hitList
            enemyGameBoard.remainingXYspots.remove(0);  // remove xyValue from remainingXYspots


        } else if (Character.toString(enemyShot.charAt(0)).equals("m")) {
            if (enemyGameBoard.hitList.size() < 2) {
                enemyGameBoard.hitList.clear();   // clear hitList
                Collections.shuffle(remainingXYspots);  // return Random xyValue from remainingXYspots
                enemyGameBoard.hitList.add(enemyGameBoard.remainingXYspots.get(0));  // add new XyValue to hitList
                enemyGameBoard.remainingXYspots.remove(0);  // remove xyValue from remainingXYspots
            }

            else if (enemyGameBoard.hitList.size() > 1) { //använd logik för nästa skott,
                enemyGameBoard.hitList.remove(enemyGameBoard.hitList.size()-1); //ta bort värdet för sista index
            }


        }

        else if (Character.toString(enemyShot.charAt(0)).equals("h")) {


            // kolla om hitList>1 isåfall ta nästa möjliga koordinat i linje med två tidigare
            // find position next to XyValue in hitList, will be  next shot
            enemyGameBoard.remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        } else if (Character.toString(enemyShot.charAt(0)).equals("s")) {
            // EnemyBoats.length tells which boat was sunk
            // logic to clear safe points around boat - add positions to guardedSpots[]
            // remove boat from ListEnemyBoats[]
            // remove guardedSpots from remainingXYspots
            enemyGameBoard.hitList.clear();   // clear hitList
            Collections.shuffle(remainingXYspots);  // return Random xyValue from remainingXYspots
            enemyGameBoard.hitList.add(enemyGameBoard.remainingXYspots.get(0));  // add new XyValue to hitList
            enemyGameBoard.remainingXYspots.remove(0);  // remove xyValue from remainingXYspots

        }
    }
}

