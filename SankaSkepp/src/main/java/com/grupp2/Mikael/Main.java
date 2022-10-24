package com.grupp2.Mikael;

public class Main {
    public static void main(String[] args) {
        DummyCoordinates dummyCoordinates = new DummyCoordinates();
        ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
        try {
            System.out.println(dummyCoordinates.matrix[4][4]);

        } catch (Exception e){

            System.out.printf("Did not work %s",e);

        }
    }
}
