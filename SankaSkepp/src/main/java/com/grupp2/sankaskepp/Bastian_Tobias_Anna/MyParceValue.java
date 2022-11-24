package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;

public class MyParceValue {
    /**
     * Author: Tobias Johansson
     */
    public int stringToXint(String shot) {
        char xChar = shot.charAt(0);
        int x = Integer.parseInt(Character.toString(xChar));
        return x;
    }

    public int stringToYint(String shot) {

        char yChar = shot.charAt(1);

        int y = 0;
        switch (yChar) {

            case 'a':
                y = 0;
                break;
            case 'b':
                y = 1;
                break;
            case 'c':
                y = 2;
                break;
            case 'd':
                y = 3;
                break;
            case 'e':
                y = 4;
                break;
            case 'f':
                y = 5;
                break;
            case 'g':
                y = 6;
                break;
            case 'h':
                y = 7;
                break;
            case 'i':
                y = 8;
                break;
            case 'j':
                y = 9;
                break;
        }

        return y;
    }
}
