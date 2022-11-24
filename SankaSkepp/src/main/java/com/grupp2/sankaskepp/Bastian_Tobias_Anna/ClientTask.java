package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.Fleet;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;
import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

/**
 * <code>ClientTask</code> - Used for connection to a server, also logic of Client.
 * @author Mikael Eriksson (mikael.eriksson@edu.edugrade.se)
 * @author Wei Li (wei.li@edu.edugrade.se)
 * @author Bastian Marx Melin (bastian.marx.melin@edu.edugrade.se)
 * @author Tobias Johansson (tobias.johansson@edu.edugrade.se)
 * @version 1.0.0
 */
public class ClientTask extends Task<Void> {

    // -----------------------------------------------------------------------------------------------------------------
    //   Properties
    // -----------------------------------------------------------------------------------------------------------------

    private Text textInBackup;
    private PrintWriter writer;
    private BufferedReader reader;
    private final GameBoard youBoard;
    private final Boolean isDebugMode;
    private String messageFromServer = "";
    private final Random rand = new Random();
    private final ControlOfInput serverAndEnemyControlOfInput;
    private ObservableStringValue clientLatestMessageText = new SimpleStringProperty("History");

    // -----------------------------------------------------------------------------------------------------------------
    //   Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructs and initializes the ClientTask object.
     *
     * @param historyTextIn {@code Text} class that comes from front-end that will be updated.
     * @param isDebugModeIn console text about what is happening in class and speeds up game
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    public ClientTask(Text historyTextIn, Boolean isDebugModeIn) {
        Fleet youFleet = new Fleet();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youPlaceBoats.placeBoats(youFleet);
        youBoard = new GameBoard(youFleet);
        Fleet serverFleet = new Fleet();
        GameBoard enemyBoard = new GameBoard();
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youFleet, serverFleet);
        textInBackup = historyTextIn;
        textInBackup.textProperty().bind(clientLatestMessageText);
        this.isDebugMode = isDebugModeIn;
    }

    // -----------------------------------------------------------------------------------------------------------------
    //   Methods
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * <code>call</code> - Extended methods from superclass <code>Task</code> it will connect to server and speak with it. (Mainline)
     *
     * @return Null
     * @throws IOException if no connection is made send error
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    @Override
    protected Void call() throws IOException {
        setupClientAndCallServer();
        clientSpeaksWithServer();
        return null;
    }

    /**
     * <code>setupClientAndCallServer</code> - Setup for socket and being able to read and write through it.
     *
     * @throws IOException if no connection is made throw exception
     * @author Wei Li
     * @since 1.0.0
     */
    private void setupClientAndCallServer() throws IOException {
        try {
            Socket clientSocket = new Socket("localhost", 1619);
            InputStream inputStream = clientSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = clientSocket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * <code>clientSpeaksWithServer</code> - Server has connected now to the client, now the communication begins.
     *
     * @throws IOException if no connection to server is made throw exception
     * @author Mikael Eriksson
     * @author Wei Li
     * @since 1.0.0
     */
    private void clientSpeaksWithServer() throws IOException {
        String outputText = serverAndEnemyControlOfInput.startRound();
        serverAndEnemyControlOfInput.sentString(outputText);
        writer.println(outputText);
        boolean isServerConnected = true;
        outputText = "";
        while (isServerConnected) {
            if (reader.ready()) {
                messageFromServer = reader.readLine();
                printMessageFromServer(isDebugMode);
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
                    outputText = "game over";
                    isServerConnected = false;
                }
                try {
                    Thread.sleep(delay(isDebugMode));
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

    /**
     * <code>printMessageFromServer</code> - prints what the client is receiving in console window, purpose is for debugging.
     *
     * @param show if true then debug message in console
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    private void printMessageFromServer(boolean show) {
        if (show) System.out.printf("Client receiving: %s\n", messageFromServer);
    }

    /**
     * <code>delay</code> - adds delay to threads or other things.
     * @param isLimited limits the speed to 2-5 seconds else 5 micro-seconds
     * @return seconds to delay
     * @author Mikael Eriksson
     * @author Wei Li
     * @since 1.0.0
     */
    private int delay(Boolean isLimited) {
        int t = rand.nextInt(6) ;
        if (isLimited) {
            return t * 50;
        } else return t * 1000;
    }

    /**
     * <code>sendGameStoppedMessage</code> - when game ends it changes output to result of battle.
     * @param outputText string to be changed to specific message
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    private void sendGameStoppedMessage(String outputText) {
        outputText = (Objects.equals(outputText, "game over")) ? "You: I lost!" : "You: I won!";
        textInBackup.textProperty().bind(new SimpleStringProperty(outputText));
    }

    // -----------------------------------------------------------------------------------------------------------------
    //   Getters & Setters
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * <code>getYouBoard</code> - gives YouBoard class.
     * @return youBoard
     * @since 1.0.0
     * @author Mikael Eriksson
     */
    public GameBoard getYouBoard() {
        return youBoard;
    }

    /**
     * <code>setTextInBackup</code> - getter for the Text class that gets feed in constructor.
     * @param textInBackup Text class that should get feed into class
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    public void setTextInBackup(Text textInBackup) {
        this.textInBackup = textInBackup;
    }
}