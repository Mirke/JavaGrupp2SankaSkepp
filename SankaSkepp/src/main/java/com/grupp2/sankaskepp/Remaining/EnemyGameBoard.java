package com.grupp2.sankaskepp.Remaining;

public class EnemyGameBoard {

    private final String[] xValue= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private final String[] yValue = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};


    private XYposition[][] remainingEnemyPositions = new XYposition[10][10];

    public String[] getxValue() {
        return xValue;
    }

    public String[] getyValue() {
        return yValue;
    }

    public XYposition[][] getRemainingEnemyPositions() {
        return remainingEnemyPositions;
    }

    public void setRemainingEnemyPositions(XYposition[][] remainingEnemyPositions) {
        this.remainingEnemyPositions = remainingEnemyPositions;
    }
}




