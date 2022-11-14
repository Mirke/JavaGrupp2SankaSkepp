package com.grupp2.sankaskepp.protokoll;


import java.util.Random;

public class ProtocolSankaSkepp implements Protocol {


    public ProtocolSankaSkepp() {
    }

    public String beginGame(int x, int y) {
        String clientFirstShot = "i";
        return String.format("%s shot %s", clientFirstShot, new DummyCoordinates().matrix[x][y]);
    }

    public String shipGotHit(int x, int y) {
        String serverShotWasHit = "h";
        return String.format("%s shot %s", serverShotWasHit, new DummyCoordinates().matrix[x][y]);
    }

    public String missedShot(int x, int y) {
        String serverShotWasMiss = "m";
        return String.format("%s shot %s", serverShotWasMiss, new DummyCoordinates().matrix[x][y]);

    }

    public String shipHasSunk(int x, int y) {
        String serverShotWasHitAndSunkenShip = "s";
        return String.format("%s shot %s", serverShotWasHitAndSunkenShip, new DummyCoordinates().matrix[x][y]);
    }

    public String sendRandomProtocolMethod(int x, int y) {
        int randomNumber = new Random().nextInt(4);

        return switch (randomNumber) {
            case 0 -> beginGame(x, y);
            case 1 -> shipGotHit(x, y);
            case 2 -> missedShot(x, y);
            case 3 -> shipHasSunk(x, y);
            default -> "Error";
        };
    }

    @Override
    public String init(int x, int y) {
        return beginGame(x, y);
    }

    @Override
    public String hit(int x, int y) {
        return shipGotHit(x, y);
    }

    @Override
    public String miss(int x, int y) {
        return missedShot(x, y);
    }

    @Override
    public String sunk(int x, int y) {
        return shipHasSunk(x, y);
    }
}
