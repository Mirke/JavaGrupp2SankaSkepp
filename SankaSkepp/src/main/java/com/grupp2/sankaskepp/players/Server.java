package com.grupp2.sankaskepp.players;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    //properties

    private PrintWriter writer;

    private BufferedReader reader;

    private boolean gameIsRunning;

    public Server() {
    }

    //Methods
    public void start() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1619);
            System.out.println("Player 1 is ready, waiting for another player.");

            //accept client´s socket
            Socket clientSocket = serverSocket.accept();
            System.out.println("Player 2 has joined the game.");

            //Create a input stream
            InputStream inputStream = clientSocket.getInputStream();


            //Create a reader
            reader = new BufferedReader( new InputStreamReader(inputStream));

            //Create a output stream
            OutputStream outputStream = clientSocket.getOutputStream();

            //Create a printwriter
            writer = new PrintWriter (outputStream,true);


        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        boolean sendMessage = true;
        Random rand = new Random();

        while(sendMessage) {
            if (reader.ready()) {
                String messageFromClient = reader.readLine();
                System.out.println("Player 2 says " + messageFromClient);



                //TODO: hit or miss depending on shot from client, need method to check result

                String coordinate = String.valueOf(rand.nextInt(10) + "." +rand.nextInt(10));
                String outputText = "m shot " + coordinate;
                System.out.println(outputText);

                try {
                    Thread.sleep(2000);
                } catch(InterruptedException e) {
                    System.out.println("Error trying to pause for two seconds with message " + e);
                }

                //TODO: add delay function
                writer.println(outputText);


                //sendMessage = false;


            }
        }

    }



}



