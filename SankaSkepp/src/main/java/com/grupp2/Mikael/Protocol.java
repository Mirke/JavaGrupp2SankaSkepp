package com.grupp2.Mikael;

public interface Protocol {

    String init(int x, int y);
    String hit(int x, int y);
    String miss(int x,int y);
    String sunk( int x, int y);

}
