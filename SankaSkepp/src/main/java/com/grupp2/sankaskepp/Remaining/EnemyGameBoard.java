package com.grupp2.sankaskepp.Remaining;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnemyGameBoard extends NewGameBoard {

    //2D-array enemy game board

    private XYposition[][] remainingEnemyPositions = new XYposition[10][10];

     //Listor för AI "next hit"
    List<String> remainingXYspots = new ArrayList<>();

    List<String> hitList = new ArrayList<>();

    List<String> guardedSpots = new ArrayList<>();

    //Getters & Setters

    public XYposition[][] getRemainingEnemyPositions() {
        return remainingEnemyPositions;
    }

    public void setRemainingEnemyPositions(XYposition[][] remainingEnemyPositions) {
        this.remainingEnemyPositions = remainingEnemyPositions;
    }

    public List<String> getRemainingXYspots() {
        return remainingXYspots;
    }

    public void setRemainingXYspots(List<String> remainingXYspots) {
        this.remainingXYspots = remainingXYspots;
    }

    public List<String> getHitList() {
        return hitList;
    }

    public void setHitList(List<String> hitList) {
        this.hitList = hitList;
    }

    public List<String> getGuardedSpots() {
        return guardedSpots;
    }

    public void setGuardedSpots(List<String> guardedSpots) {
        this.guardedSpots = guardedSpots;
    }
}




