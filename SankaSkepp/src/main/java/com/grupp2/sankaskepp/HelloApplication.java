package com.grupp2.sankaskepp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.io.IOException;

public class HelloApplication extends Application {

    private boolean gameIsOn = false;
    private GameBoard attackerBoard, defenderBoard;

    private boolean attackerTurn = false;
    private boolean defenderTurn = false;
    private Random random = new Random();


    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Amazing battleship game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 700);
        // KONTROLLPANEL?
        root.setRight(new TextArea("Kanske en kontrollpanel här som håller koll vilka kordinater " +
                "                   som lades och om träff eller miss"));
        attackerBoard = new GameBoard(true, false);
        defenderBoard = new GameBoard(false, true);

        VBox vBox = new VBox(50, attackerBoard, defenderBoard);
        vBox.setAlignment(Pos.TOP_CENTER);

        root.setCenter(vBox);

        return root;
    }
}