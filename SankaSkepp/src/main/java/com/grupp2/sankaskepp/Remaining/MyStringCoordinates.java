package com.grupp2.sankaskepp.Remaining;

import java.util.List;

/*
    Karins kod i denna klass
 */
public class MyStringCoordinates {

    // Attributes
    private EnemyGameBoard enemyGameBoard = new EnemyGameBoard();

    private YourGameBoard yourGameBoard = new YourGameBoard();

    private XYposition xyPosition = new XYposition();

    private XYposition[][] remainingEnemyPositions;

    private List<String> remainingXYspots;

    // Constructors
    public MyStringCoordinates() {


        //Make List of all positions in EnemyGameBoard - Karin

        remainingXYspots = (List<String>) enemyGameBoard.getRemainingXYspots();

        remainingEnemyPositions = enemyGameBoard.getRemainingEnemyPositions();


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                remainingEnemyPositions[i][j] = new XYposition
                        (enemyGameBoard.getxValue()[i], enemyGameBoard.getyValue()[j], false, false);

                yourGameBoard.setYourPositions(remainingEnemyPositions);

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
}
