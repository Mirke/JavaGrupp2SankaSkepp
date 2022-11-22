package com.grupp2.sankaskepp.Remaining;

import java.util.ArrayList;
import java.util.List;

/*
    Karins kod i denna klass
 */
public class MyStringXY{

    // Attributes
    private EnemyGameBoard enemyGameBoard = new EnemyGameBoard();

    private YourGameBoard yourGameBoard = new YourGameBoard();

    private XYposition xyPosition = new XYposition();


    private XYposition[][] remainingEnemyPositions = new XYposition[10][10];
    private List<String> remainingEnemyPositions2 = new ArrayList<>();

    private List<String> remainingXYspots;

    // Constructors
    public MyStringXY() {

        //Make List of all positions in EnemyGameBoard - Karin

        remainingXYspots = enemyGameBoard.getRemainingXYspots();

        remainingEnemyPositions = enemyGameBoard.getRemainingEnemyPositions();


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("I loop");
                remainingEnemyPositions[i][j] = new XYposition
                        (enemyGameBoard.getxValue()[i], enemyGameBoard.getyValue()[j], false, false);

                yourGameBoard.setYourPositions(remainingEnemyPositions);

                //Förändrat
                remainingEnemyPositions2.add(remainingEnemyPositions[i][j].getXyValue());
                System.out.println(remainingEnemyPositions[i][j].getXyValue());

                remainingXYspots.add(remainingEnemyPositions[i][j].getXyValue());
            }

        }
    }

    // Methods

    // Getters
    public EnemyGameBoard getEnemyGameBoard() {
        return enemyGameBoard;
    }

    public YourGameBoard getYourGameBoard() {
        return yourGameBoard;
    }

    public XYposition getXyPosition() {
        return xyPosition;
    }

    public XYposition[][] getRemainingEnemyPositions() {
        return remainingEnemyPositions;
    }

    public List<String> getRemainingXYspots() {
        return remainingXYspots;
    }

    public List<String> getRemainingEnemyPositions2() {
        return remainingEnemyPositions2;
    }
}
