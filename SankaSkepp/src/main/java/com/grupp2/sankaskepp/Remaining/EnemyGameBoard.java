package com.grupp2.sankaskepp.Remaining;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnemyGameBoard {

    private final String[] xValue= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private final String[] yValue = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};


    private XYposition[][] remainingEnemyPositions = new XYposition[10][10];

    Set<String> remainingXYspots = new HashSet<>();


    public String[] getxValue() {
        return xValue;
    }

    public String[] getyValue() {
        return yValue;
    }

    public XYposition[][] getRemainingEnemyPositions() {
        return remainingEnemyPositions;
    }

    public Set<String> getRemainingXYspots() {
        return remainingXYspots;
    }

    public void setRemainingXYspots(Set<String> remainingXYspots) {
        this.remainingXYspots = remainingXYspots;

    }

    public void setRemainingEnemyPositions(XYposition[][] remainingEnemyPositions) {
        this.remainingEnemyPositions = remainingEnemyPositions;
    }
}




