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
    private Boolean isClientConnected;
    private final Random rand = new Random();
    private final ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
    private String messageFromClient = "";
    private String messageFromServer = "";
    private ObservableStringValue serverLatestMessageText = new SimpleStringProperty("init\n");
    private Text textInBackup;

    @Override
    protected Void call() throws Exception {
        try {
            setupClientAndCallServer();
            clientSpeaksWithServer();

        } catch (IOException e) {
            isClientConnected = false;
        }
        return null;
    }

    // Weis kod
    private void setupClientAndCallServer() throws IOException {
        Socket clientSocket = new Socket("localhost", 1619);
        InputStream inputStream = clientSocket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = clientSocket.getOutputStream();
        writer = new PrintWriter(outputStream, true);
    }

    private void clientSpeaksWithServer() throws IOException {
        while (isClientConnected) {
            if (reader.ready()) {
                messageFromClient = reader.readLine();
                printMessageFromServer(true);
                latestMessageFromServer();
                clientUpdateMessage();
                printMessageOutFromClient(true);
                sendClientMessageToServer();
                latestMessageSentFromClient();
            }
        }
        sendGameStoppedMessage();
    }

    private void printMessageFromServer(boolean show) {
        if (show) System.out.printf("Server receiving: %s\n", messageFromClient);
    }

    private void clientUpdateMessage() {
        messageFromServer = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10));
    }

    private void printMessageOutFromClient(boolean show) {
        if(show) System.out.printf("Server sending: %s\n", messageFromServer);
    }

    private void latestMessageFromServer() {
        String editedMessage = String.format("""
                You: %s          
                """,messageFromClient);
        serverLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(serverLatestMessageText);
    }

    private void latestMessageSentFromClient() {
        String editedMessage = String.format("""
                Enemy: %s          
                """,messageFromServer);
        serverLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(serverLatestMessageText);
    }

    private void sendClientMessageToServer() {
        try {
            Thread.sleep(delay() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writer.println(messageFromServer);
    }

    private int delay() {
        int t = (int) (Math.random() * 5 + 1);
        if (t == 1) {
            t++;
        }
        return t;
    }

    public void setClientConnected(Boolean clientConnected) {
        isClientConnected = clientConnected;
    }

    private void sendGameStoppedMessage() {
        textInBackup.textProperty().bind(new SimpleStringProperty("Game stopped"));
    }

}