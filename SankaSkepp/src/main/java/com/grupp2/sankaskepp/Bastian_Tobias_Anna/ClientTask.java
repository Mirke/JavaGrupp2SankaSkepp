package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;
import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

public class ClientTask extends Task<Void> {

    public Text textInBackup;
    private PrintWriter writer;
    private BufferedReader reader;
    private final GameBoard youBoard;
    private final GameBoard enemyBoard;
    private String messageFromServer = "";
    private final Random rand = new Random();
    private final ControlOfInput serverAndEnemyControlOfInput;
    private ObservableStringValue clientLatestMessageText = new SimpleStringProperty("History");

    public ClientTask(Text historyTextIn) {
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.placeBoats(youBoat);
        youBoard = new GameBoard(youBoat);
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        enemyBoard = new GameBoard();
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youBoat, serverBoat);
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
        String outputText = serverAndEnemyControlOfInput.startRound();
        serverAndEnemyControlOfInput.sentString(outputText);
        writer.println(outputText);
        Boolean isServerConnected = true;
        outputText = "";
        while (isServerConnected) {
            if (reader.ready()) {
                messageFromServer = reader.readLine();
                printMessageFromServer(true);
                String editedMessage = String.format("Enemy: %s", messageFromServer);
                clientLatestMessageText = new SimpleStringProperty(editedMessage);
                textInBackup.textProperty().bind(clientLatestMessageText);
                if (!messageFromServer.contains("game over")) {
                    outputText = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromServer);
                } else {
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }
                if (outputText.contains("game over")) {
                    isServerConnected = false;
                    outputText = "game over";
                }
                try {
                    Thread.sleep(50);
                    editedMessage = String.format("You: %s", outputText);
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

    private void printMessageFromServer(boolean show) {
        if (show) System.out.printf("Client receiving: %s\n", messageFromServer);
    }

    private int delay() {
        int t = rand.nextInt(5);
        if (t == 1) {
            t++;
        }
        return t * 1000;
    }

    private void sendGameStoppedMessage(String outputText) {
        outputText = (Objects.equals(outputText, "game over")) ? "You: I lost!" : "You: I won!";
        textInBackup.textProperty().bind(new SimpleStringProperty(outputText));
    }

    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }
}