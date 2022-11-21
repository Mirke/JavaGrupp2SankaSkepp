package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import com.grupp2.sankaskepp.Remaining.MyStringCoordinates;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerTask extends Task<Void> {

    private PrintWriter writer;
    private BufferedReader reader;
    private Boolean isClientConnected;
    private final Random rand = new Random();
    private String messageFromClient = "";
    private String messageFromServer = "";
    private ObservableStringValue serverLatestMessageText = new SimpleStringProperty("History");
    public Text textInBackup;

    private GameBoard youBoard;

    private MyStringCoordinates myStringCoordinates = new MyStringCoordinates();

    private ControlOfInput serverAndEnemyControlOfInput;
    private GameBoard enemyBoard;

    public ServerTask(Text historyTextIn) {

        //Init - Start
        // Tobias { ***********
        // you
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
         //youPlaceBoats.initializeGridArray();
        youPlaceBoats.placeBoats(youBoat);
        youBoard = new GameBoard(youBoat);
        //ComputerAI youAI = new ComputerAI(youBoat);
        //ControlOfInput youControlOfInput = new ControlOfInput(youBoard);

        // -------------------------------------------

        // Server
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        //serverPlaceBoats.initializeGridArray();
        //serverPlaceBoats.placeBoats(serverBoat.getBoats());
        enemyBoard = new GameBoard();
        // ComputerAI serverAI = new ComputerAI();

        // skickar in spelplanerna för att kunna få färg på cellerna när de blir beskjutna
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youBoat, serverBoat);
        //Init - End

        textInBackup = historyTextIn;
        textInBackup.textProperty().bind(serverLatestMessageText);
    }

    @Override
    protected Void call() {
        try {
            setupServerAndListenForClient();
            serverSpeaksWithClient();

        } catch (IOException e) {
            isClientConnected = false;
        }
        return null;
    }

    // Weis kod
    private void setupServerAndListenForClient() throws IOException {
        System.out.println("Server ON");
        ServerSocket serverSocket = new ServerSocket(1619);
        Socket clientSocket = serverSocket.accept();
        isClientConnected = true;
        InputStream inputStream = clientSocket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = clientSocket.getOutputStream();
        writer = new PrintWriter(outputStream, true);
    }

    private void serverSpeaksWithClient() throws IOException {
        String outputText = "";
        while (isClientConnected) {
            if (reader.ready()) {
                messageFromClient = reader.readLine();

                String pos = "";
                if (!messageFromClient.contains("game over")) {
                    outputText = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromClient);
                    //serverAndEnemyControlOfInput.sentString(outputText);
                } else {
                    System.out.println("I won");
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }


                if (outputText.contains("game over")) {
                    System.out.println("I lost");
                    isClientConnected = false;
                    outputText = "game over";

                }
                /*
                    Collections.shuffle(myStringCoordinates.getRemainingXYspots());
                    pos = myStringCoordinates.getRemainingXYspots().get(0);
                    myStringCoordinates.getRemainingXYspots().remove(0);


                    serverAndEnemyControlOfInput.sentString(outputText);
               */

                //printMessageFromClient(false);
                //latestMessageFromClient();
                //serverUpdateMessage();
                //String editedMessage = String.format("""
                //You: %s""", outputText);
                //serverLatestMessageText = new SimpleStringProperty(editedMessage);
                //textInBackup.textProperty().bind(serverLatestMessageText);
                //printMessageOutFromServer(false);

                try {
                    Thread.sleep(delay() * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                writer.println(outputText);
                //sendServerMessageToClient();
                //latestMessageSentFromServer();
            }
        }
        sendGameStoppedMessage(outputText);
    }

    private int delay() {
        int t = 1;
        if (t == 1) {
            t++;
        }
        return t;
    }

    private void sendGameStoppedMessage(String outputText) {
        if(outputText == "game over"){
            outputText = "Enemy: I lost!";
        } else {
            outputText = "Enemy: I won!";
        }
        textInBackup.textProperty().bind(new SimpleStringProperty(outputText));
    }

    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }
}

