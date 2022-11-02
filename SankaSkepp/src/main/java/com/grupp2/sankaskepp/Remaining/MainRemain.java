package com.grupp2.sankaskepp.Remaining;

public class MainRemain {
    public static void main(String[] args) throws InterruptedException{

        //random values between 1-5, representing random time delay
        for (int i = 0; i < 10; i++) {
            int t = (int) (Math.random() * 5 + 1);
            Thread.sleep(t*1000);
            System.out.println(t);


        EnemyGameBoard enemyGameBoard = new EnemyGameBoard();


        //Make 2D-array of all positions in EnemyGameBoard - doesn't work

        XYposition[][] remainingEnemyPositions = enemyGameBoard.getRemainingEnemyPositions();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                remainingEnemyPositions[i][j] =
                        new XYposition(enemyGameBoard.getxValue()[i], enemyGameBoard.getyValue()[j]);


                System.out.println(remainingEnemyPositions[i][j].getXyValue());
            }

        }
    }
    }
