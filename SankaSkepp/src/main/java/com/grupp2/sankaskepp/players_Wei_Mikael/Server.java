package com.grupp2.sankaskepp.players_Wei_Mikael;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Wei
 */
public class Server implements Runnable{
    //properties
    private GameBoard youBoard;


    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    private ControlOfInput serverAndEnemyControlOfInput;

    private GameBoard enemyBoard;

    private PrintWriter writer;

    private BufferedReader reader;


    public String rightNow = "";

    public Server() {
        // Tobias { ***********

        // you
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.initializeGridArray();
        youPlaceBoats.placeBoats(youBoat.getBoats());
        youBoard = new GameBoard(youBoat);


        // -------------------------------------------

        // Server
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        serverPlaceBoats.initializeGridArray();

        enemyBoard = new GameBoard();


        // skickar in spelplanerna för att kunna få färg på cellerna när de blir beskjutna
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youBoat,serverBoat);


    }

    //Methods
    public void start() throws IOException, InterruptedException {
        try {
            ServerSocket serverSocket = new ServerSocket(1619);
            System.out.println("Server is ready, waiting for client.");

            //accept client´s socket
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client has joined the game.");

            //Create a input stream
            InputStream inputStream = clientSocket.getInputStream();


            //Create a reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Create a output stream
            OutputStream outputStream = clientSocket.getOutputStream();

            //Create a print writer
            writer = new PrintWriter(outputStream, true);


        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        boolean sendMessage = true;

        while (sendMessage) {
            if (reader.ready()) {
                String messageFromClient = reader.readLine();


                System.out.println("Server receiving: " + messageFromClient);

                this.rightNow = messageFromClient;


                //TODO: hit or miss depending on shot from client, need method to check result



                String text = "";
                if(!messageFromClient.contains("game over")) {
                    text = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromClient);
                }
                else{
                    System.out.println("I won");
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }

                String outputText = "";
                if(text.contains("game over")){
                    System.out.println("I lost");
                    sendMessage = false;
                    outputText = "game over";

                }else{
                    serverAndEnemyControlOfInput.sentString(text);
                }





                System.out.println("Server sending: " + outputText);
                System.out.println();




                writer.println(outputText);





            }
        }

    }


    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}




