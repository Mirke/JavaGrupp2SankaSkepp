package com.grupp2.sankaskepp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Random;
import java.io.IOException;

/**
 * För att köra , höger-klicka på pom.xml filen och välja "add as Maven project"  så ska allt rött försvinna.
 */

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
        scene.getStylesheets().add("BattleshipStyle.css");
        primaryStage.setTitle("Amazing battleship game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Parent createContent() {

        //Anna härifrån och ner

        VBox root = new VBox();
        root.setPrefSize(1200, 760);

        DropShadow dropShadow = new DropShadow();


        HBox title = new HBox();
        Text titleLabel = new Text("Battleship");
        titleLabel.setFill(Color.web("#b2dee4"));
        titleLabel.setEffect(dropShadow);
        title.getStyleClass().add("title");
        title.setPrefHeight(150);
        title.setAlignment(Pos.TOP_CENTER);

        HBox history = new HBox();
        Text historyText = new Text("Test");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");

        title.getChildren().add(titleLabel);

        //TextArea history = new TextArea();
        //history.setStyle("-fx-background-color: transparent");
        //history.setEditable(false);
        //history.appendText("Test");

        VBox you = new VBox();
        you.getStyleClass().add("enemyAndYou");
        you.setAlignment(Pos.BOTTOM_LEFT);

        //Pane filler = new Pane();
        //HBox.setHgrow(filler, Priority.ALWAYS);

        VBox enemy = new VBox();
        enemy.getStyleClass().add("enemyAndYou");
        enemy.setAlignment(Pos.BOTTOM_RIGHT);


        Text youLabel = new Text("You");
        youLabel.setFill(Color.web( "#b938e2"));
        youLabel.setEffect(dropShadow);
        Text enemyLabel = new Text("Enemy");
        enemyLabel.setFill(Color.web("#b938e2"));
        enemyLabel.setEffect(dropShadow);


        attackerBoard = new GameBoard(true, false);
        attackerBoard.setEffect(dropShadow);
        defenderBoard = new GameBoard(false, true);
        defenderBoard.setEffect(dropShadow);

        you.getChildren().addAll(youLabel, attackerBoard);
        enemy.getChildren().addAll(enemyLabel, defenderBoard);


        Button startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        Button stopButton = new Button("Stop");
        stopButton.setEffect(dropShadow);

        //startButton.setOnAction(e ->);

        //stopButton.setOnAction(e ->);

        VBox startButtonBox = new VBox(startButton);
        startButtonBox.getStyleClass().add("startButtonBox");
        VBox stopButtonBox = new VBox(stopButton);
        stopButtonBox.getStyleClass().add("stopButtonBox");

        startButton.setAlignment(Pos.CENTER);
        stopButton.setAlignment(Pos.CENTER);

        HBox bottomBox = new HBox(40,startButtonBox,stopButtonBox);


        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title,history,boards,bottomBox);
        history.setAlignment(Pos.BASELINE_CENTER);

        bottomBox.setAlignment(Pos.BOTTOM_CENTER);



        //Vbox ships = new VBox(new Text("Boats"));
        //ships.setAlignment(Pos.CENTER_RIGHT);
        //root.setRight(ships);


        return root;
        // hej

        // KONTROLLPANEL?
        //root.setRight(new TextArea("Kanske en kontrollpanel här som håller koll vilka kordinater " +
        //     "                   som lades och om träff eller miss"));
    }
}