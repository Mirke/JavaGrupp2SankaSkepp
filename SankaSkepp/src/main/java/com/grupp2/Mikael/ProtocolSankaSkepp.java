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

    public String shipHasSunk() {
        String serverShotWasHitAndSunkenShip = "s";
        return String.format("%s shot 5f", serverShotWasHitAndSunkenShip);
    }

    public String playerLostGame() {
        String serverShotDownAllPlayerShips = "game over";
        return serverShotDownAllPlayerShips;
    }


    public String beginGame(int x, int y) {
        String clientFirstShot = "i";
        return String.format("%s shot %s",clientFirstShot , new DummyCoordinates().matrix[x][y]);
    }

    public String shipGotHit(int x, int y) {
        String serverShotWasHit = "h";
        return String.format("%s shot %s",serverShotWasHit , new DummyCoordinates().matrix[x][y]);
    }

    public String missedShot(int x, int y) {
        String serverShotWasMiss = "m";
        return String.format("%s shot %s",serverShotWasMiss , new DummyCoordinates().matrix[x][y]);

    }

    public String shipHasSunk(int x, int y) {
        String serverShotWasHitAndSunkenShip = "s";
        return String.format("%s shot %s",serverShotWasHitAndSunkenShip , new DummyCoordinates().matrix[x][y]);
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

    @Override
    public String init(int x, int y) {
        return beginGame(x,y);
    }

    @Override
    public String hit(int x, int y) {
        return shipGotHit(x,y);
    }

    @Override
    public String miss(int x, int y) {
        return missedShot(x,y);
    }

    @Override
    public String sunk(int x, int y) {
        return shipHasSunk(x,y);
    }
}
