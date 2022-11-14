
package com.grupp2.sankaskepp.Bastian_Tobias_Anna;

import com.grupp2.sankaskepp.players.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HelloController {

    Text historyText = new Text("test");
    Thread threadForServer;
    Thread threadForClient;

    public HelloController(Text historyText) {
        this.historyText = historyText;
    }

    public EventHandler<ActionEvent> startButtonHandler() {
        return event -> {
            threadForServer = new Thread(new ServerTask(historyText));
            threadForServer.setDaemon(true);
            threadForServer.start();

            threadForClient = new Thread(new ClientTask());
            threadForClient.setDaemon(true);
            threadForClient.start();
        };
    }
}