package com.grupp2.Mikael;

public class Main {
    public static void main(String[] args) {
        DummyCoordinates dummyCoordinates = new DummyCoordinates();
        ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
        try {
            dummyCoordinates.getCoordinates(1, Letter.b);

        } catch (Exception e){

            System.out.printf("Did not work %s",e);

        }
    }
}
