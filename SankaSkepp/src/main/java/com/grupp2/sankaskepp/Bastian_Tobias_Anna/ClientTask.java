package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
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
    public Text textInBackup;

    private GameBoard youBoard, enemyBoard;
    private Position position = new Position();
    private Position position2 = new Position();
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
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard,youBoat,serverBoat);
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
        Collections.shuffle(position.getAllCoordinates());
        String pos = position.getAllCoordinates().get(0);
        position.getAllCoordinates().remove(0);
        serverAndEnemyControlOfInput.sentString("i shot ".concat(pos));
        writer.println("i shot ".concat(pos));
        // init - Stop

       // writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10), rand.nextInt(10)));
        isServerConnected = true;

        while (isServerConnected) {
            if (reader.ready()) {
                messageFromServer = reader.readLine();
                // init -start

                String editedMessage = String.format("""
                Enemy: %s""", messageFromServer);
                clientLatestMessageText = new SimpleStringProperty(editedMessage);
                textInBackup.textProperty().bind(clientLatestMessageText);

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
                    isServerConnected = false;
                    outputText = "game over";

                }else{
                    Collections.shuffle(position.getAllCoordinates());
                    pos = position.getAllCoordinates().get(0);
                    position.getAllCoordinates().remove(0);

                    outputText = text.concat(" shot ").concat(pos);
                    serverAndEnemyControlOfInput.sentString(outputText);
                }
                // init - end

                printMessageFromServer(true);
                //latestMessageFromServer();
                //clientUpdateMessage();
                printMessageOutFromClient(true, outputText);
                //sendClientMessageToServer();

                //latestMessageSentFromClient();

                try {
                    Thread.sleep(delay() * 1000);
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
        sendGameStoppedMessage();
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
        int t = (int) (Math.random() * 5 + 1);
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