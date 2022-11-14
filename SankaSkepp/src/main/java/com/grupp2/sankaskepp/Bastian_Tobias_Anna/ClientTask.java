package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ClientTask extends Task<Void> {

    private PrintWriter writer;
    private BufferedReader reader;
    private Boolean isServerConnected = true;
    private final Random rand = new Random();
    private final ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
    private String messageFromClient = "";
    private String messageFromServer = "";
    private ObservableStringValue clientLatestMessageText = new SimpleStringProperty("init\n");
    private Text textInBackup;

    public ClientTask() {
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
        writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10), rand.nextInt(10)));
        isServerConnected = true;
        while (isServerConnected) {
            if (reader.ready()) {
                messageFromServer = reader.readLine();
                printMessageFromServer(true);
               // latestMessageFromServer();
                clientUpdateMessage();
                printMessageOutFromClient(false);
                sendClientMessageToServer();
                //latestMessageSentFromClient();
            }
        }
        sendGameStoppedMessage();
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
                You: %s          
                """, messageFromServer);
        clientLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(clientLatestMessageText);
    }

    private void latestMessageSentFromClient() {
        String editedMessage = String.format("""
                Enemy: %s          
                """, messageFromServer);
        clientLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(clientLatestMessageText);
    }

    private void sendClientMessageToServer() {
        try {
            Thread.sleep(delay() * 1000);
            writer.println(messageFromClient);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int delay() {
        int t = (int) (Math.random() * 5 + 1);
        if (t == 1) {
            t++;
        }
        return t;
    }

    public void setClientConnected(Boolean clientConnected) {
        isServerConnected = clientConnected;
    }

    private void sendGameStoppedMessage() {
        textInBackup.textProperty().bind(new SimpleStringProperty("Game stopped"));
    }

}