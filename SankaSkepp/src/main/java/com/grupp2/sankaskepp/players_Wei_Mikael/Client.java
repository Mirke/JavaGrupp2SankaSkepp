package com.grupp2.sankaskepp.players_Wei_Mikael;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.Bastian_Tobias_Anna.Position;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.Fleet;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import java.io.*;
import java.net.Socket;
import java.util.Random;


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

    private Position position = new Position();
    private Position position2 = new Position();

    private ControlOfInput serverAndEnemyControlOfInput;


    private boolean firstGuess;

    // Constructor
    public Client() {
        // you
        Fleet youFleet = new Fleet();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youPlaceBoats.placeBoats(youFleet);
        youBoard = new GameBoard(youFleet);
        //ComputerAI youAI = new ComputerAI(youBoat);
        //ControlOfInput youControlOfInput = new ControlOfInput(youBoard);

        // -------------------------------------------

        // Server
        Fleet serverFleet = new Fleet();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        //serverPlaceBoats.placeBoats(serverBoat.getBoats());
        enemyBoard = new GameBoard();
        // ComputerAI serverAI = new ComputerAI();

        // skickar in spelplanerna för att kunna få färg på cellerna när de blir beskjutna
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youFleet, serverFleet);

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
        Random rand = new Random();
        boolean sendMessage = true;
        int i = 0;
        while (sendMessage) {
            if (reader.ready()) {
                String messageFromServer = reader.readLine();
                System.out.println("Client receiving: " + messageFromServer);

                String text = "";
                if (!messageFromServer.contains("game over")) {
                    text = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromServer);
                } else {
                    System.out.println("I won");
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }
                String outputText = "";
                if (text.contains("game over")) {
                    System.out.println("I lost");
                    sendMessage = false;
                    outputText = "game over";
                } else {
                    serverAndEnemyControlOfInput.sentString(text);
                }
                System.out.println("Client sending: " + outputText);
                System.out.println();


                //TODO: add delay function
                //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

                int t = (int) (Math.random() * 5 + 1);
                if (t == 1) {
                    t++;
                }

                writer.println(outputText);
                //sendMessage = false;
            }


        }

    }

}

