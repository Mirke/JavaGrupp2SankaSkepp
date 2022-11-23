package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class HelloController {

    Text historyText = new Text("History");
    Thread threadForServer;
    Thread threadForClient;
    ServerTask serverTask = new ServerTask(historyText, false);
    ClientTask clientTask = new ClientTask(historyText,false);
    public GameBoard youBoard = clientTask.getYouBoard();
    public GameBoard enemyBoard = serverTask.getYouBoard();

    public HelloController(Text historyTextIn) {
        this.historyText = historyTextIn;
    }

    public EventHandler<ActionEvent> startButtonHandler() {
        return event -> {
            serverTask.setTextInBackup(this.historyText);
            threadForServer = new Thread(serverTask);
            threadForServer.setDaemon(true);
            threadForServer.start();

            clientTask.setTextInBackup(this.historyText);
            threadForClient = new Thread(clientTask);
            threadForClient.setDaemon(true);
            threadForClient.start();
        };
    }
}