package com.grupp2.Lekpaket;

public abstract class Position {
    protected Object value;
    protected Letter letter;

    public Position(Object value) {
        this.value = value;
    }

    public Position(Letter letter) {
        this.letter = letter;
    }
}
