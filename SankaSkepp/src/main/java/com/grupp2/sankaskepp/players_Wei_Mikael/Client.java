package com.grupp2.sankaskepp.players_Wei_Mikael;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

import java.io.*;
import java.net.Socket;



/**
 * Author: Wei
 */
public class Client {

    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }

    //properties
    private GameBoard youBoard, enemyBoard;

    private PrintWriter writer;

    private BufferedReader reader;


    private ControlOfInput serverAndEnemyControlOfInput;



    // Constructor
    public Client() {
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
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard,youBoat,serverBoat);

    }

    //Methods
    public void start() throws IOException, InterruptedException {
        try {
            //clients socket connected to server
            Socket clientSocket = new Socket("localhost", 1619);

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


        writer.println(serverAndEnemyControlOfInput.controlOtherPlayerString("k shot qq"));




        boolean sendMessage = true;

        while (sendMessage) {
            if (reader.ready()) {
                String messageFromServer = reader.readLine();
                System.out.println("Client receiving: " + messageFromServer);







                String text = "";
                if(!messageFromServer.contains("game over")) {
                    text = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromServer);
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


                System.out.println("Client sending: " + outputText);
                System.out.println();


            }


        }

    }

}

