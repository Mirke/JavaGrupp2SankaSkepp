package com.grupp2.sankaskepp.players_Wei_Mikael;

import java.io.IOException;
/**
 * Author: Wei
 */
public class ServerStart {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.start();
    }
}
