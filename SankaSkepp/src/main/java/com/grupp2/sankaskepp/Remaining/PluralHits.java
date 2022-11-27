package com.grupp2.sankaskepp.Remaining;

public class PluralHits { //Karin all external XY conditions for logic
    //Inlagt i ComputerLogic för att få spelet att fungera, denna klass används ej
    char actual;

    public PluralHits() {
    }


    public char getActual() {
        return actual;
    }

    public void setActual(char actual) {
        this.actual = actual;
    }


    public void safeSpots(int p, int q, boolean topdown) {


        if (p == 0 && q == 0) {
            actual = 'N';
        } else if (p == 0 && q == 9) {
            actual = 'W';
        } else if (p == 9 && q == 0) {
            actual = 'E';
        } else if (p == 9 && q == 9) {
            actual = 'S';
        } else if (p == 0) {
            actual = 'L';
        } else if (p == 9) {
            actual = 'R';
        } else if (q == 0) {
            actual = 'T';
        } else if (q == 9) {
            actual = 'B';
        } else if (p >= 1 && p <= 8 && q <= 8 && q >= 1) {
            actual = 'C';
        }
    }
}




