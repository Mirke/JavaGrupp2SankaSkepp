package com.grupp2.sankaskepp.Remaining;

import java.util.List;
import java.util.Set;

public class MainRemain {
    public static void main(String[] args) throws InterruptedException{

        //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

            int t = (int) (Math.random() * 5 + 1);
            if (t == 1) {
                t++;
            }
            Thread.sleep(t*1000);



        EnemyGameBoard enemyGameBoard = new EnemyGameBoard();


        //Make 2D-array of all positions in EnemyGameBoard

        Set<String> remainingXYspots = enemyGameBoard.getRemainingXYspots();

        XYposition[][] remainingEnemyPositions = enemyGameBoard.getRemainingEnemyPositions();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                remainingEnemyPositions[i][j] =
                        new XYposition(enemyGameBoard.getxValue()[i], enemyGameBoard.getyValue()[j]);

                remainingXYspots.add(remainingEnemyPositions[i][j].getXyValue());

                //System.out.println(remainingEnemyPositions[i][j].getXyValue());
            }

        }

        System.out.println(remainingXYspots);
    }
}
