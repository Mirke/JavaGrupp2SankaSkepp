package com.grupp2.sankaskepp.players;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.Bastian_Tobias_Anna.Position;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
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
        /*
         * Mikael kod: START
         */

     // ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
        /*
         * Mikael kod: END
         */

        Collections.shuffle(position.getAllCoordinates());
        String pos = position.getAllCoordinates().get(0);
        position.getAllCoordinates().remove(0);
        serverAndEnemyControlOfInput.sentString("i shot ".concat(pos));
        writer.println("i shot ".concat(pos));


        Random rand = new Random();
        //String coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10)); // Commenting out Mikael
      //  writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10), rand.nextInt(10)));


        boolean sendMessage = true;
        int i = 0;
        while (sendMessage) {
            if (reader.ready()) {
                String messageFromServer = reader.readLine();
                //System.out.println("Player 1 says " + messageFromServer); //Mikael commenting out
                System.out.println("Client receiving: " + messageFromServer);


                //Collections.shuffle(position.getAllCoordinates());





                pos = "";
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
                    Collections.shuffle(position.getAllCoordinates());
                    pos = position.getAllCoordinates().get(0);
                    position.getAllCoordinates().remove(0);

                    outputText = text.concat(" shot ").concat(pos);
                    serverAndEnemyControlOfInput.sentString(outputText);
                }


                //  ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
                //position.remove(position.getAllCoordinates());



                //TODO: hit or miss depending on shot from server, need method to check result

                //coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10));// going to edit Mikael
                //String outputText = "m shot " + coordinate; // going to edit Mikael
               // String outputText = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10)); //Mikaels kod
                //System.out.println(outputText); // commenting and changing code Mikael
                System.out.println("Client sending: " + outputText);
                System.out.println();


                //TODO: add delay function
                //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

                int t = (int) (Math.random() * 5 + 1);
                if (t == 1) {
                    t++;
                }
                //Thread.sleep(t * 1000);

                //System.out.println("Client sending: ");


                writer.println(outputText);
                //sendMessage = false;
            }


        }

    }

}

