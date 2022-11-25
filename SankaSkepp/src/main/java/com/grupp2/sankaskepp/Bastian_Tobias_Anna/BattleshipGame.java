package com.grupp2.sankaskepp.Bastian_Tobias_Anna;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class BattleshipGame extends Application {

    private GameBoard youBoard, enemyBoard;
    private HelloController helloController;
    private DropShadow dropShadow = new DropShadow();

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

        HBox history = new HBox();
        Text historyText = new Text("History");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        helloController = new HelloController(historyText);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");

        HBox submarine = new HBox();
        Text submarineLabel = new Text("4 submarines");
        submarineLabel.setFill(Color.web("37a8b7"));
        submarineLabel.setEffect(dropShadow);
        submarine.setAlignment(Pos.TOP_RIGHT);
        submarine.getChildren().add(submarineLabel);

        HBox cruiser = new HBox();
        Text cruiserLabel = new Text("3 cruisers");
        cruiserLabel.setFill(Color.web("37a8b7"));
        cruiserLabel.setEffect(dropShadow);
        cruiser.setAlignment(Pos.TOP_RIGHT);
        cruiser.getChildren().add(cruiserLabel);

        HBox battleship = new HBox();
        Text battleshipLabel = new Text("2 battleships");
        battleshipLabel.setFill(Color.web("37a8b7"));
        battleshipLabel.setEffect(dropShadow);
        battleship.setAlignment(Pos.TOP_RIGHT);
        battleship.getChildren().add(battleshipLabel);

        HBox carrier = new HBox();
        Text carrierLabel = new Text("1 carrier");
        carrierLabel.setFill(Color.web("37a8b7"));
        carrierLabel.setEffect(dropShadow);
        carrier.setAlignment(Pos.BOTTOM_RIGHT);
        carrier.getChildren().add(carrierLabel);

        VBox boatBox = new VBox();
        submarine.setPadding(new Insets(70, 180, 0, 0));
        cruiser.setPadding(new Insets(78, 80, 0, 0));
        battleship.setPadding(new Insets(405, 80, 0, 0));
        carrier.setPadding(new Insets(90, 120, 0, 0));
        boatBox.getChildren().addAll(submarine, cruiser, battleship, carrier);



        Region filler = new Region();
        filler.setPrefHeight(50);
        HBox.setHgrow(filler, Priority.ALWAYS);

        VBox you = new VBox();
        you.getStyleClass().add("enemyAndYou");
        you.setAlignment(Pos.BOTTOM_LEFT);

        VBox enemy = new VBox();
        enemy.getStyleClass().add("enemyAndYou");
        enemy.setAlignment(Pos.BOTTOM_RIGHT);

        Text youLabel = new Text("You");
        youLabel.setFill(Color.web("#b938e2"));
        youLabel.setEffect(dropShadow);
        Text enemyLabel = new Text("Enemy");
        enemyLabel.setFill(Color.web("#b938e2"));
        enemyLabel.setEffect(dropShadow);

        // Tobias { ***********
        this.enemyBoard = helloController.enemyBoard;
        this.youBoard = helloController.youBoard;
        // ********  } Tobias

        you.getChildren().addAll(youLabel, youBoard);
        enemy.getChildren().addAll(enemyLabel, enemyBoard);


        Button startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        Button stopButton = new Button("Stop");
        stopButton.setEffect(dropShadow);

        startButton.setOnAction(helloController.startButtonHandler());


        VBox startButtonBox = new VBox(startButton);
        startButtonBox.getStyleClass().add("startButtonBox");
        VBox stopButtonBox = new VBox(stopButton);
        stopButtonBox.getStyleClass().add("stopButtonBox");

        startButton.setAlignment(Pos.CENTER);


        HBox bottomBox = new HBox(40, startButtonBox);

        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(createTitle(), history, filler, boards, bottomBox);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        history.setAlignment(Pos.BASELINE_CENTER);
        boatBox.setAlignment(Pos.BASELINE_RIGHT);
        StackPane stack = new StackPane(boatBox, root);

        return stack;
    }

    private HBox createTitle() {
        HBox title = new HBox();
        Text titleLabel = new Text("Battleship");
        titleLabel.setFill(Color.web("#b2dee4"));
        titleLabel.setEffect(dropShadow);
        title.getStyleClass().add("title");
        title.setPrefHeight(70);
        title.setAlignment(Pos.TOP_CENTER);
        title.getChildren().add(titleLabel);
        return title;
    }
}