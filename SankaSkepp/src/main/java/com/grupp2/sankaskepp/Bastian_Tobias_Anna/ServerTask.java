package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import com.grupp2.sankaskepp.Remaining.MyStringCoordinates;
import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Random;

public class ServerTask extends Task<Void> {

    private PrintWriter writer;
    private BufferedReader reader;
    private Boolean isClientConnected;
    private final Random rand = new Random();
    private final ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
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
        while (isClientConnected) {
            if (reader.ready()) {
                messageFromClient = reader.readLine();

                String pos = "";
                String text = "";
                if (!messageFromClient.contains("game over")) {
                    text = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromClient);
                } else {
                    System.out.println("I won");
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }

                String outputText = "";
                if (text.contains("game over")) {
                    System.out.println("I lost");
                    isClientConnected = false;
                    outputText = "game over";

                } else {
                    Collections.shuffle(myStringCoordinates.getRemainingXYspots());
                    pos = myStringCoordinates.getRemainingXYspots().get(0);
                    myStringCoordinates.getRemainingXYspots().remove(0);

                    outputText = text.concat(" shot ").concat(pos);
                    serverAndEnemyControlOfInput.sentString(outputText);
                }

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
        sendGameStoppedMessage();
    }

    private void printMessageFromClient(boolean show) {
        if (show) System.out.printf("Server receiving: %s\n", messageFromClient);
    }

    private void serverUpdateMessage() {
        messageFromServer = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10));
    }

    private void printMessageOutFromServer(boolean show) {
        if (show) System.out.printf("Server sending: %s\n", messageFromServer);
    }

    private void latestMessageFromClient() {
        String editedMessage = String.format("""
                You: %s""", messageFromClient);
        serverLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(serverLatestMessageText);
    }

    private void latestMessageSentFromServer() {
        String editedMessage = String.format("""
                Enemy: %s""", messageFromServer);
        serverLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(serverLatestMessageText);
    }


    private void sendServerMessageToClient() {
        try {
            Thread.sleep(delay() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writer.println(messageFromServer);
    }

    private int delay() {
        int t = 1;
        if (t == 1) {
            t++;
        }
        return t;
    }

    private void sendGameStoppedMessage() {
        textInBackup.textProperty().bind(new SimpleStringProperty("Game stopped"));
    }

    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }
}

