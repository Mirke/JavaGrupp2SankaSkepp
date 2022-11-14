package com.grupp2.sankaskepp.players;

import java.io.IOException;

/**
 * Author: Wei
 */
public class ClientStart {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.start();
    }
}
