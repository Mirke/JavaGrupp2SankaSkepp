package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.Fleet;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.scene.text.Text;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * <code>ServerTask</code> - Used for hosting and letting clients connect, also logic of Server.
 *
 * @author Mikael Eriksson (mikael.eriksson@edu.edugrade.se)
 * @author Wei Li (wei.li@edu.edugrade.se)
 * @author Bastian Marx Melin (bastian.marx.melin@edu.edugrade.se)
 * @author Tobias Johansson (tobias.johansson@edu.edugrade.se)
 * @version 1.0.0
 */
public class ServerTask extends Task<Void> {

    // -----------------------------------------------------------------------------------------------------------------
    //   Properties
    // -----------------------------------------------------------------------------------------------------------------

    public Text textInBackup;
    private PrintWriter writer;
    private BufferedReader reader;
    private final GameBoard youBoard;
    private Boolean isClientConnected;
    private final Boolean isDebugMode;
    private final Random rand = new Random();
    private final ControlOfInput serverAndEnemyControlOfInput;

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
    public ServerTask(Text historyTextIn, Boolean isDebugModeIn) {
        Fleet youFleet = new Fleet();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youPlaceBoats.placeBoats(youFleet);
        youBoard = new GameBoard(youFleet);
        Fleet serverFleet = new Fleet();
        GameBoard enemyBoard = new GameBoard();
        serverAndEnemyControlOfInput = new ControlOfInput(youBoard, enemyBoard, youFleet, serverFleet);
        textInBackup = historyTextIn;
        ObservableStringValue serverLatestMessageText = new SimpleStringProperty("History");
        textInBackup.textProperty().bind(serverLatestMessageText);
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
    protected Void call() {
        try {
            setupServerAndListenForClient();
            serverSpeaksWithClient();
        } catch (IOException e) {
            isClientConnected = false;
        }
        return null;
    }

    /**
     * <code>setupServerAndListenForClient</code> - Setup for ServerSocket and being able to read and write through it.
     *
     * @throws IOException if no connection is made throw exception
     * @author Wei Li
     * @since 1.0.0
     */
    private void setupServerAndListenForClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1619);
        Socket clientSocket = serverSocket.accept();
        isClientConnected = true;
        InputStream inputStream = clientSocket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = clientSocket.getOutputStream();
        writer = new PrintWriter(outputStream, true);
    }

    /**
     * <code>serverSpeaksWithClient</code> - Server has connected now to the client, now the communication begins.
     *
     * @throws IOException if no connection to server is made throw exception
     * @author Mikael Eriksson
     * @author Wei Li
     * @since 1.0.0
     */
    private void serverSpeaksWithClient() throws IOException {
        String outputText = "";
        while (isClientConnected) {
            if (reader.ready()) {
                String messageFromClient = reader.readLine();
                printMessageFromClient(isDebugMode, messageFromClient);
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
                    Thread.sleep(delay(isDebugMode));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                writer.println(outputText);
            }
        }
        sendGameStoppedMessage(outputText);
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
        int t = rand.nextInt(6);
        if (isLimited) {
            return t * 50;
        } else return t * 1000;
    }

    /**
     * <code>printMessageFromClient</code> - prints what the client is receiving in console window, purpose is for debugging.
     *
     * @param show if true then debug message in console
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    private void printMessageFromClient(boolean show, String outputText) {
        if (show) System.out.printf("Server receiving: %s\n", outputText);
    }

    /**
     * <code>sendGameStoppedMessage</code> - when game ends it changes output to result of battle.
     * @param outputText string to be changed to specific message
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    private void sendGameStoppedMessage(String outputText) {
        outputText = (outputText == "game over") ? "Enemy: I lost!" : "Enemy: I won!";
        textInBackup.textProperty().bind(new SimpleStringProperty(outputText));
    }

    // -----------------------------------------------------------------------------------------------------------------
    //   Getter & Setter
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

