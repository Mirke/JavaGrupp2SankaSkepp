package com.grupp2.sankaskepp.players;

import com.grupp2.sankaskepp.Bastian_Tobias.GameBoard;
import com.grupp2.sankaskepp.Bastian_Tobias.Position;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Random;

/**
 * Author: Wei
 */
public class Server {
    //properties
    private GameBoard youBoard;


    private Position position = new Position();
    private Position position2 = new Position();

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

    private boolean gameIsRunning;

    public Server() {
        // Tobias { ***********

        // you
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.initializeGridArray();
        youPlaceBoats.placeBoats(youBoat.getBoats());
        youBoard = new GameBoard(youBoat);
        //ComputerAI youAI = new ComputerAI(youBoat);
        //ControlOfInput youControlOfInput = new ControlOfInput(youBoard);

        // -------------------------------------------

        // Server
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        serverPlaceBoats.initializeGridArray();
        //serverPlaceBoats.placeBoats(serverBoat.getBoats());
        enemyBoard = new GameBoard();
        // ComputerAI serverAI = new ComputerAI();

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

            //Create a printwriter
            writer = new PrintWriter(outputStream, true);


        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        boolean sendMessage = true;
        Random rand = new Random();
        int i = 0;

        while (sendMessage) {
            if (reader.ready()) {
                String messageFromClient = reader.readLine();

                //System.out.println("Player 2 says " + messageFromClient);
                System.out.println("Server receiving: " + messageFromClient);


                //TODO: hit or miss depending on shot from client, need method to check result
                /*
                 * Mikael kod: START
                 */
                //position.shuffleList(position.getAllCoordinates());
                Collections.shuffle(position2.getAllCoordinates());
                String pos = position2.getAllCoordinates().get(0);
                position2.getAllCoordinates().remove(0);



                String text = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromClient);

                if(text.contains("game over")){
                    System.out.println("I lost");
                    sendMessage = false;
                    break;
                    //System.exit(0);
                }
                //  ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
                //position.remove(position.getAllCoordinates());
                String outputText = text.concat(" shot ").concat(pos);
                if(!outputText.contains("game over")) {
                    serverAndEnemyControlOfInput.sentString(outputText);
                }
                /*
                 * Mikael kod: END
                 */
                String coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10));
                //String outputText = "m shot " + coordinate; //commenting out to merge code Mikael
                //String outputText = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10)); //Mikaels kod
                System.out.println("Server sending: " + outputText);
                System.out.println();
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Error trying to pause for two seconds with message " + e);
                }*/

                //delay function

                //System.out.print("Server sending: ");

                //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

                int t = (int) (Math.random() * 5 + 1);
                if (t == 1) {
                    t++;
                }
                 //Thread.sleep(t * 1000);

                writer.println(outputText);


                //sendMessage = false;


            }
        }

    }


}




