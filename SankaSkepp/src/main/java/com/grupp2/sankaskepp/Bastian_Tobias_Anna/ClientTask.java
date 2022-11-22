package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import com.grupp2.sankaskepp.Remaining.MyStringXY;
import com.grupp2.sankaskepp.Remaining.MyStringXY;
import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.Random;

public class ClientTask extends Task<Void> {

    private PrintWriter writer;
    private BufferedReader reader;
    private Boolean isServerConnected = true;
    private final Random rand = new Random();
    private final ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
    private String messageFromClient = "";
    private String messageFromServer = "";
    private ObservableStringValue clientLatestMessageText = new SimpleStringProperty("History");
    //private MyStringXY myStringXY = new MyStringXY();
    public Text textInBackup;

    private GameBoard youBoard, enemyBoard;
    private ControlOfInput serverAndEnemyControlOfInput;


    public ClientTask(Text historyTextIn) {
        // Init - Start
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
        //Init - Slut


        textInBackup = historyTextIn;
        textInBackup.textProperty().bind(clientLatestMessageText);
    }

    @Override
    protected Void call() throws IOException {
        setupClientAndCallServer();
        clientSpeaksWithServer();
        return null;
    }

    // Weis kod
    private void setupClientAndCallServer() throws IOException {
        try {
            System.out.println("Client trying to connect to Server...");
            Socket clientSocket = new Socket("localhost", 1619);
            InputStream inputStream = clientSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = clientSocket.getOutputStream();
            writer = new PrintWriter(outputStream, true);


        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private void clientSpeaksWithServer() throws IOException {
        // init -Start
        /*
        Collections.shuffle(myStringCoordinates.getRemainingXYspots());
        String pos = myStringCoordinates.getRemainingXYspots().get(0);
        myStringCoordinates.getRemainingXYspots().remove(0);
        serverAndEnemyControlOfInput.sentString("i shot ".concat(pos));
        writer.println("i shot ".concat(pos));
        */
        String outputText = serverAndEnemyControlOfInput.startRound();
        serverAndEnemyControlOfInput.sentString(outputText);
        writer.println(outputText);
        // init - Stop

        // writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10), rand.nextInt(10)));
        isServerConnected = true;

        outputText = "";
        while (isServerConnected) {
            if (reader.ready()) {
                messageFromServer = reader.readLine();
                // init -start

                String editedMessage = String.format("""
                        Enemy: %s""", messageFromServer);
                clientLatestMessageText = new SimpleStringProperty(editedMessage);
                textInBackup.textProperty().bind(clientLatestMessageText);


                if (!messageFromServer.contains("game over")) {
                    outputText = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromServer);
                    serverAndEnemyControlOfInput.sentString(outputText);
                } else {
                    System.out.println("I won");
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }



                if (outputText.contains("game over")) {
                    System.out.println("I lost");
                    isServerConnected = false;
                    outputText = "game over";

                }
                    /*
                    Collections.shuffle(myStringCoordinates.getRemainingXYspots());
                    pos = myStringCoordinates.getRemainingXYspots().get(0);
                    myStringCoordinates.getRemainingXYspots().remove(0);

                    outputText = text.concat(" shot ").concat(pos);
                    serverAndEnemyControlOfInput.sentString(outputText);
                     */

                // init - end

                printMessageFromServer(true);
                //latestMessageFromServer();
                //clientUpdateMessage();
                printMessageOutFromClient(true, outputText);
                //sendClientMessageToServer();

                //latestMessageSentFromClient();

                try {
                    Thread.sleep(1000);
                    editedMessage = String.format("""
                            You: %s""", outputText);
                    clientLatestMessageText = new SimpleStringProperty(editedMessage);
                    textInBackup.textProperty().bind(clientLatestMessageText);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                writer.println(outputText);
            }
        }
        sendGameStoppedMessage(outputText);
    }

    private void printMessageOutFromClient(boolean show, String outputText) {
        if (show) System.out.printf("Client sending: %s\n", outputText);
    }

    private void printMessageFromServer(boolean show) {
        if (show) System.out.printf("Client receiving: %s\n", messageFromServer);
    }

    private void clientUpdateMessage() {
        messageFromClient = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10));
    }

    private void printMessageOutFromClient(boolean show) {
        if (show) System.out.printf("Client sending: %s\n", messageFromClient);
    }

    private void latestMessageFromServer() {
        String editedMessage = String.format("""
                You: %s""", messageFromServer);
        clientLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(clientLatestMessageText);
    }

    private void latestMessageSentFromClient() {
        String editedMessage = String.format("""
                You: %s""", messageFromServer);
        clientLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(clientLatestMessageText);
    }

    private void sendClientMessageToServer() {
        try {
            Thread.sleep(delay() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writer.println(messageFromClient);
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
            outputText = "You: I lost!";
        } else {
            outputText = "You: I won!";
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