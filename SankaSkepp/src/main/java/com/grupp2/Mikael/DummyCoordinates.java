package com.grupp2.Mikael;

import java.util.Arrays;
import java.util.List;

public class DummyCoordinates implements Coordinate {

    public String[][] matrix = {
            {"0a", "a1", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a"},
            {"0b", "1b", "2b", "3b", "4b", "5b", "6b", "7b", "8b", "9b"},
            {"0c", "1c", "2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c"},
            {"0d", "1d", "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d"},
            {"0e", "1e", "2e", "3e", "4e", "5e", "6e", "7e", "8e", "9e"},
            {"0f", "1f", "2f", "3f", "4f", "5f", "6f", "7f", "8f", "9f"},
            {"0g", "1g", "2g", "3g", "4g", "5g", "6g", "7g", "8g", "9g"},
            {"0h", "1h", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h"},
            {"0i", "1i", "2i", "3i", "4i", "5i", "6i", "7i", "8i", "9i"},
            {"0j", "1j", "2j", "3j", "4j", "5j", "6j", "7j", "8j", "9j"}
    };

    public Cell[][] matrixOfCells = {
            {new Cell("0a"), new Cell("a1"), new Cell("2a"), new Cell("3a"), new Cell("4a"), new Cell("5a"), new Cell("6a"), new Cell("7a"), new Cell("8a"), new Cell("9a")},
            {new Cell("0b"), new Cell("1b"), new Cell("2b"), new Cell("3b"), new Cell("4b"), new Cell("5b"), new Cell("6b"), new Cell("7b"), new Cell("8b"), new Cell("9b")},
            {new Cell("0c"), new Cell("1c"), new Cell("2c"), new Cell("3c"), new Cell("4c"), new Cell("5c"), new Cell("6c"), new Cell("7c"), new Cell("8c"), new Cell("9c")},
            {new Cell("0d"), new Cell("1d"), new Cell("2d"), new Cell("3d"), new Cell("4d"), new Cell("5d"), new Cell("6d"), new Cell("7d"), new Cell("8d"), new Cell("9d")},
            {new Cell("0e"), new Cell("1e"), new Cell("2e"), new Cell("3e"), new Cell("4e"), new Cell("5e"), new Cell("6e"), new Cell("7e"), new Cell("8e"), new Cell("9e")},
            {new Cell("0f"), new Cell("1f"), new Cell("2f"), new Cell("3f"), new Cell("4f"), new Cell("5f"), new Cell("6f"), new Cell("7f"), new Cell("8f"), new Cell("9f")},
            {new Cell("0g"), new Cell("1g"), new Cell("2g"), new Cell("3g"), new Cell("4g"), new Cell("5g"), new Cell("6g"), new Cell("7g"), new Cell("8g"), new Cell("9g")},
            {new Cell("0h"), new Cell("1h"), new Cell("2h"), new Cell("3h"), new Cell("4h"), new Cell("5h"), new Cell("6h"), new Cell("7h"), new Cell("8h"), new Cell("9h")},
            {new Cell("0i"), new Cell("1i"), new Cell("2i"), new Cell("3i"), new Cell("4i"), new Cell("5i"), new Cell("6i"), new Cell("7i"), new Cell("8i"), new Cell("9i")},
            {new Cell("0j"), new Cell("1j"), new Cell("2j"), new Cell("3j"), new Cell("4j"), new Cell("5j"), new Cell("6j"), new Cell("7j"), new Cell("8j"), new Cell("9j")}
    };

    public DummyCoordinates() {
        int selectedX = 0;
        int selectedY = 0;
    }

    public String getCoordinates(int x, Letter y) {
        List list = Arrays.asList(matrix);
        return matrix[getX(x)][getY(y)];
    }


    @Override
    public int getX(int number) throws IndexOutOfBoundsException {
        if (number < 10 && number > 0) {
            throw new IndexOutOfBoundsException("Number outside range 0-9");
        }
        return number;
    }

    @Override
    public int getY(Letter letter) {
        return letter.ordinal();
    }
}
