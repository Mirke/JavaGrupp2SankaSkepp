package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;
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
    private final ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
    private String messageFromClient = "";
    private String messageFromServer = "";
    private ObservableStringValue serverLatestMessageText = new SimpleStringProperty("init\n");
    private Text textInBackup;

    public ServerTask(Text historyTextIn) {
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
                printMessageFromClient(true);
                latestMessageFromClient();
                serverUpdateMessage();
                printMessageOutFromServer(false);
                sendServerMessageToClient();
                latestMessageSentFromServer();
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
                You: %s          
                """, messageFromClient);
        serverLatestMessageText = new SimpleStringProperty(editedMessage);
        textInBackup.textProperty().bind(serverLatestMessageText);
    }

    private void latestMessageSentFromServer() {
        String editedMessage = String.format("""
                Enemy: %s          
                """, messageFromServer);
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

