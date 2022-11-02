package com.grupp2.sankaskepp.players;

import java.io.IOException;

public class ServerStart {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}
