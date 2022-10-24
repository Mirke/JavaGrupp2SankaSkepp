package com.grupp2.Mikael;

public class ProtocolSankaSkepp implements Protocol{


    public String beginGame() {
         String clientFirstShot = "i";
         return String.format("%s shot 6c", clientFirstShot);
    }

    public String shipGotHit() {
        String serverShotWasHit = "h";
        return String.format("%s shot 7b", serverShotWasHit);
    }

    public String missedShot() {
        String serverShotWasMiss = "m";
        return String.format("%s shot 6g", serverShotWasMiss);

    }

    public String playerLostGame() {
        String serverShotDownAllPlayerShips = "game over";
        return serverShotDownAllPlayerShips;
    }

    public String shipHasSunk() {
        String serverShotWasHitAndSunkenShip = "s";
        return String.format("%s shot 5f", serverShotWasHitAndSunkenShip);
    }



    @Override
    public String init() {
        return beginGame();
    }

    @Override
    public String hit() {
        return shipGotHit();
    }

    @Override
    public String miss() {
        return missedShot();
    }

    @Override
    public String gameOver() {
        return playerLostGame();
    }

    @Override
    public String sunk() {
        return shipHasSunk();
    }
}
