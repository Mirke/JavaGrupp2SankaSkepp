package com.grupp2.sankaskepp.Bastian_Tobias_Anna;


import com.grupp2.sankaskepp.players_Wei_Mikael.Client;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleshipGame extends Application {

    private GameBoard youBoard, enemyBoard;

    HelloController helloController;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
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

    private Parent createContent() throws IOException, InterruptedException {

        //Anna härifrån och ner

        VBox root = new VBox();
        root.setPrefSize(1200, 760);

        DropShadow dropShadow = new DropShadow();


        HBox title = new HBox();
        Text titleLabel = new Text("Battleship");
        titleLabel.setFill(Color.web("#b2dee4"));
        titleLabel.setEffect(dropShadow);
        title.getStyleClass().add("title");
        title.setPrefHeight(70);
        title.setAlignment(Pos.TOP_CENTER);

        HBox history = new HBox();
        Text historyText = new Text("History");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);

        helloController = new HelloController(historyText);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");


        Text submarineText = new Text("4 submarines");
        submarineText.setFill(Color.web("37a8b7"));
        submarineText.setEffect(dropShadow);
        HBox submarine = new HBox();

        Text cruiserText = new Text("3 cruisers");
        cruiserText.setFill(Color.web("37a8b7"));
        cruiserText.setEffect(dropShadow);
        HBox cruiser = new HBox();

        Text battleshipText = new Text("2 battleships");
        battleshipText.setFill(Color.web("37a8b7"));
        battleshipText.setEffect(dropShadow);
        HBox battleship = new HBox();

        Text hangarshipText = new Text("1 hangarship");
        hangarshipText.setFill(Color.web("37a8b7"));
        hangarshipText.setEffect(dropShadow);
        HBox hangarship = new HBox();

        //HBox submarine = new HBox();
        //submarine.setStyle("-fx-background-image: url('submarine.png');" + "-fx-background-position: center center;" );



        title.getChildren().add(titleLabel);

        //TextArea history = new TextArea();
        //history.setStyle("-fx-background-color: transparent");
        //history.setEditable(false);
        //history.appendText("Test");

        Region filler = new Region();
        filler.setPrefHeight(50);
        HBox.setHgrow(filler, Priority.ALWAYS);

        VBox you = new VBox();
        you.getStyleClass().add("enemyAndYou");
        you.setAlignment(Pos.BOTTOM_LEFT);

        //Pane filler = new Pane();
        //HBox.setHgrow(filler, Priority.ALWAYS);

        VBox enemy = new VBox();
        enemy.getStyleClass().add("enemyAndYou");
        enemy.setAlignment(Pos.BOTTOM_RIGHT);


        Text youLabel = new Text("You");
        youLabel.setFill(Color.web("#b938e2"));
        youLabel.setEffect(dropShadow);
        Text enemyLabel = new Text("Enemy");
        enemyLabel.setFill(Color.web("#b938e2"));
        enemyLabel.setEffect(dropShadow);

        /************************************************************************
         * TODO: svar från motståndaren: har vi träffat eller missat?
         * den informationen ska målas upp på motståndarens bräde (server)
         * behöver veta var den inforamtionen kommer ifrån
         * BYT NAMN: server ska bli enemy.
         **********************************************************************/
        // Tobias { ***********

        //this.enemyBoard = server.getEnemyBoard();
        //this.youBoard = server.getYouBoard();
        this.enemyBoard = helloController.enemyBoard;
        this.youBoard = helloController.youBoard;
        // klass där AI spelar mot varann
        // TheBattle theBattle = new TheBattle(enemyBoard,serverBoard,enemyAI,serverAI);

        // ********  } Tobias


        you.getChildren().addAll(youLabel, youBoard);
        enemy.getChildren().addAll(enemyLabel, enemyBoard);


        Button startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        Button stopButton = new Button("Stop");
        stopButton.setEffect(dropShadow);

        startButton.setOnAction(helloController.startButtonHandler());

        //stopButton.setOnAction(e ->);

        VBox startButtonBox = new VBox(startButton);
        startButtonBox.getStyleClass().add("startButtonBox");
        VBox stopButtonBox = new VBox(stopButton);
        stopButtonBox.getStyleClass().add("stopButtonBox");

        startButton.setAlignment(Pos.CENTER);
        //stopButton.setAlignment(Pos.CENTER);

        HBox bottomBox = new HBox(40, startButtonBox);


        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, history, filler, boards, bottomBox, submarine, cruiser);
        history.setAlignment(Pos.BASELINE_CENTER);

        bottomBox.setAlignment(Pos.BOTTOM_CENTER);


        //Vbox ships = new VBox(new Text("Boats"));
        //ships.setAlignment(Pos.CENTER_RIGHT);
        //root.setRight(ships);


        return root;
    }
}