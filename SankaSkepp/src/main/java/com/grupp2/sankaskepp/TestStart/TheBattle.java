package com.grupp2.sankaskepp.TestStart;

import com.grupp2.sankaskepp.GameBoard;

import java.util.Iterator;

public class TheBattle {
    // attributes
    private GameBoard enemyBoard, serverBoard;
    private ComputerAI enemyAI, serverAI;

    private String attackerShot;
    private boolean defenderIsHit;

    // Enemy
    private String enemyShot;
    private boolean enemyHit;

    // Server
    private String serverShot;
    private boolean serverHit;

    // constructors
    public TheBattle(GameBoard enemyBoard, GameBoard serverBoard, ComputerAI enemyAI, ComputerAI serverAI) {
        this.enemyBoard = enemyBoard;
        this.serverBoard = serverBoard;
        this.enemyAI = enemyAI;
        this.serverAI = serverAI;


        Iterator iteratorAttacker = enemyAI.getAllCoordinates().listIterator();
        Iterator iteratorDefender = serverAI.getAllCoordinates().listIterator();

        // före remove
        // serverAI.getAllShipCoordinates();

        // enemyAI skjuter första skottet
        firstShot(enemyAI, serverAI);
        this.serverBoard.parceStringShotCoordinates(defenderIsHit, attackerShot);

        // behöver hjälp med spel logiken

        /*
        while (iteratorDefender.hasNext()) {
            // Server skjuter
            shotsFired(serverAI, enemyAI);
            this.enemyBoard.parceStringShotCoordinates(defenderIsHit, attackerShot);
            System.out.println("Server: " + defenderIsHit + " || " + attackerShot);

            if (enemyAI.isAnyShipLeft()) {
                System.out.println("Server won");
                return;
            }
            while (iteratorAttacker.hasNext()) {
                // nu skjuter enemyAI
                shotsFired(enemyAI,serverAI);
                this.serverBoard.parceStringShotCoordinates(defenderIsHit, attackerShot);
                if(serverAI.isAnyShipLeft()){
                    System.out.println("Enemy won");
                    return;
                }

                // efter remove
                // serverAI.getAllShipCoordinates();
                System.out.println("Enemy: " + defenderIsHit + " || " + attackerShot);
            }
        }

         */
    }

    // methods
    private void firstShot(ComputerAI attackerAI, ComputerAI defenderAI) {
        attackerShot = attackerAI.shot();
        defenderIsHit = defenderAI.isHit(attackerShot);
    }

    // pun intended
    private void shotsFired(ComputerAI attackerAI, ComputerAI defenderAI) {
        attackerShot = attackerAI.shot();
        defenderIsHit = defenderAI.isHit(attackerShot);
    }

    // getter n setters
    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    public GameBoard getServerBoard() {
        return serverBoard;
    }

    public ComputerAI getEnemyAI() {
        return enemyAI;
    }

    public ComputerAI getServerAI() {
        return serverAI;
    }
}
