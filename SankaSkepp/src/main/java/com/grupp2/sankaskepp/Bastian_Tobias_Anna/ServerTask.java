package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerTask extends Task<Void> {

    public Text textInBackup;
    private PrintWriter writer;
    private BufferedReader reader;
    private final GameBoard youBoard;
    private Boolean isClientConnected;
    private final GameBoard enemyBoard;
    private final Random rand = new Random();
    private final ControlOfInput serverAndEnemyControlOfInput;

    public ServerTask(Text historyTextIn) {
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.placeBoats(youBoat);
        youBoard = new GameBoard(youBoat);
        Boat serverBoat = new Boat();
        serverBoat.createBoats();
        enemyBoard = new GameBoard();
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youBoat, serverBoat);
        textInBackup = historyTextIn;
        ObservableStringValue serverLatestMessageText = new SimpleStringProperty("History");
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

    private void setupServerAndListenForClient() throws IOException {
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
                String messageFromClient = reader.readLine();
                printMessageOutIntoServer(true, messageFromClient);
                if (!messageFromClient.contains("game over")) {
                    outputText = serverAndEnemyControlOfInput.controlOtherPlayerString(messageFromClient);
                } else {
                    serverAndEnemyControlOfInput.getAnswer().add("s");
                    serverAndEnemyControlOfInput.checkAnswerFromOtherPlayer();
                    break;
                }
                if (outputText.contains("game over")) {
                    isClientConnected = false;
                    outputText = "game over";
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                writer.println(outputText);
            }
        }
        sendGameStoppedMessage(outputText);
    }

    private int delay() {
        int t = rand.nextInt(5);
        if (t == 1) {
            t++;
        }
        return t * 1000;
    }

    private void printMessageOutIntoServer(boolean show, String outputText) {
        if (show) System.out.printf("Server receiving: %s\n", outputText);
    }

    private void sendGameStoppedMessage(String outputText) {
        outputText = (outputText == "game over") ? "Enemy: I lost!" : "Enemy: I won!";
        textInBackup.textProperty().bind(new SimpleStringProperty(outputText));
    }

    public GameBoard getYouBoard() {
        return youBoard;
    }

    public GameBoard getEnemyBoard() {
        return enemyBoard;
    }
}

