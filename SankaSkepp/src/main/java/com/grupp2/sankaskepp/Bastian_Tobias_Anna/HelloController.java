package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

/**
 * <code>HelloController</code> - Controller class that handles discussion between front-end and back-end.
 * @author Mikael Eriksson (mikael.eriksson@edu.edugrade.se)
 * @since 1.0.0
 */
public class HelloController {

    // -----------------------------------------------------------------------------------------------------------------
    //   Properties
    // -----------------------------------------------------------------------------------------------------------------

    Text historyText = new Text("History");
    Thread threadForServer;
    Thread threadForClient;
    ServerTask serverTask = new ServerTask(historyText, false);
    ClientTask clientTask = new ClientTask(historyText,false);
    public GameBoard youBoard = clientTask.getYouBoard();
    public GameBoard enemyBoard = serverTask.getYouBoard();

    // -----------------------------------------------------------------------------------------------------------------
    //   Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor for HelloController
     * @param historyTextIn from front-end which is getting used in threads back-end.
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    public HelloController(Text historyTextIn) {
        this.historyText = historyTextIn;
    }

    // -----------------------------------------------------------------------------------------------------------------
    //   Methods
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * <code>startButtonHandler</code> - Event handler that starts the threads and the game.
     * @return EventHandler As a button I get pushed, then I start game and threads.
     * @author Mikael Eriksson
     * @since 1.0.0
     */
    public EventHandler<ActionEvent> startButtonHandler() {
        return event -> {
            /* Server thread*/
            serverTask.setTextInBackup(this.historyText);
            threadForServer = new Thread(serverTask);
            threadForServer.setDaemon(true);
            threadForServer.start();

            /*Client thread*/
            clientTask.setTextInBackup(this.historyText);
            threadForClient = new Thread(clientTask);
            threadForClient.setDaemon(true);
            threadForClient.start();
        };
    }
}