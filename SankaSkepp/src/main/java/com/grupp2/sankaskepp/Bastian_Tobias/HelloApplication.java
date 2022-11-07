package com.grupp2.sankaskepp.Bastian_Tobias;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Parent;


import java.io.IOException;

public class HelloApplication extends Application {

    private GameBoard youBoard, enemyBoard;

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
        you.getStyleClass().add("serverAndYou");
        you.setAlignment(Pos.BOTTOM_LEFT);

        //Pane filler = new Pane();
        //HBox.setHgrow(filler, Priority.ALWAYS);

        VBox server = new VBox();
        server.getStyleClass().add("serverAndYou");
        server.setAlignment(Pos.BOTTOM_RIGHT);


        Text youLabel = new Text("You");
        youLabel.setFill(Color.web("#b938e2"));
        youLabel.setEffect(dropShadow);
        Text serverLabel = new Text("Server");
        serverLabel.setFill(Color.web("#b938e2"));
        serverLabel.setEffect(dropShadow);

        // Tobias { ***********

        // you = DU
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.initializeGridArray();
        youPlaceBoats.placeBoats(youBoat.getBoats());
        youBoard = new GameBoard(youBoat);
       // ComputerAI youAI = new ComputerAI(youBoat);
       // ControlOfInput youControlOfInput = new ControlOfInput(youBoard);

        // -------------------------------------------

        // Server = ENEMY
        Boat enemyBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        enemyBoat.createBoats();
        serverPlaceBoats.initializeGridArray();
        serverPlaceBoats.placeBoats(enemyBoat.getBoats());
        enemyBoard = new GameBoard();
      //  ComputerAI serverAI = new ComputerAI();
        ControlOfInput serverControlOfInput = new ControlOfInput(youBoard,enemyBoard);

        // klass där AI spelar mot varann
        // TheBattle theBattle = new TheBattle(enemyBoard,serverBoard,enemyAI,serverAI);

        // ********  } Tobias


        you.getChildren().addAll(youLabel, youBoard);
        server.getChildren().addAll(serverLabel, enemyBoard);


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

        HBox bottomBox = new HBox(40, startButtonBox, stopButtonBox);


        HBox boards = new HBox(50, you, server);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, history, boards, bottomBox);
        history.setAlignment(Pos.BASELINE_CENTER);

        bottomBox.setAlignment(Pos.BOTTOM_CENTER);

        //Vbox ships = new VBox(new Text("Boats"));
        //ships.setAlignment(Pos.CENTER_RIGHT);
        //root.setRight(ships);

        return root;
    }
}