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

    Text historyText = new Text("History");
    private HelloController helloController;
    private final DropShadow dropShadow = new DropShadow();

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

        //Anna härifrån och ner (ändring 25/11: lade in det mesta i metoder).
        VBox root = new VBox();
        root.setPrefSize(1200, 760);

        /* Mikael - START */
        helloController = new HelloController(historyText);
        /* Mikael - SLUT */

        // Tobias { ***********
        this.enemyBoard = helloController.enemyBoard;
        this.youBoard = helloController.youBoard;
        // ********  } Tobias

        root.getChildren().addAll(createTitle(), createHistory(), createFiller(), createBoards(),createBottomBox());
        StackPane stack = new StackPane(createBoatBox(), root);

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

    private HBox createHistory() {
        HBox history = new HBox();
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");
        history.setAlignment(Pos.BASELINE_CENTER);
        return history;
    }

    private HBox createSubmarine() {
        HBox submarine = new HBox();
        Text submarineLabel = new Text("4 submarines");
        submarineLabel.setFill(Color.web("37a8b7"));
        submarineLabel.setEffect(dropShadow);
        submarine.setAlignment(Pos.TOP_RIGHT);
        submarine.getChildren().add(submarineLabel);
        submarine.setPadding(new Insets(70, 180, 0, 0));
        return submarine;
    }
        private HBox createCruiser() {
        HBox cruiser = new HBox();
        Text cruiserLabel = new Text("3 cruisers");
        cruiserLabel.setFill(Color.web("37a8b7"));
        cruiserLabel.setEffect(dropShadow);
        cruiser.setAlignment(Pos.TOP_RIGHT);
        cruiser.getChildren().add(cruiserLabel);
        cruiser.setPadding(new Insets(78, 80, 0, 0));
        return cruiser;
        }

        private HBox createBattleship() {
        HBox battleship = new HBox();
        Text battleshipLabel = new Text("2 battleships");
        battleshipLabel.setFill(Color.web("37a8b7"));
        battleshipLabel.setEffect(dropShadow);
        battleship.setAlignment(Pos.TOP_RIGHT);
        battleship.getChildren().add(battleshipLabel);
        battleship.setPadding(new Insets(405, 80, 0, 0));
        return battleship;
        }

        private HBox createCarrier() {
        HBox carrier = new HBox();
        Text carrierLabel = new Text("1 carrier");
        carrierLabel.setFill(Color.web("37a8b7"));
        carrierLabel.setEffect(dropShadow);
        carrier.setAlignment(Pos.BOTTOM_RIGHT);
        carrier.getChildren().add(carrierLabel);
        carrier.setPadding(new Insets(90, 120, 0, 0));
        return carrier;
        }

        private VBox createBoatBox() {
        VBox boatBox = new VBox();

        boatBox.getChildren().addAll(createSubmarine(), createCruiser(), createBattleship(), createCarrier());
        boatBox.setAlignment(Pos.BASELINE_RIGHT);
        return boatBox;
        }

        private Region createFiller() {
        Region filler = new Region();
        filler.setPrefHeight(50);
        HBox.setHgrow(filler, Priority.ALWAYS);
        return filler;
        }

        private VBox createYou() {
        VBox you = new VBox();
        Text youLabel = new Text("You");
        youLabel.setFill(Color.web("#b938e2"));
        youLabel.setEffect(dropShadow);
        you.getStyleClass().add("enemyAndYou");
        you.setAlignment(Pos.BOTTOM_LEFT);
        you.getChildren().addAll(youLabel, youBoard);
        return you;
        }

        private VBox createEnemy() {
        VBox enemy = new VBox();
        Text enemyLabel = new Text("Enemy");
        enemyLabel.setFill(Color.web("#b938e2"));
        enemyLabel.setEffect(dropShadow);
        enemy.getStyleClass().add("enemyAndYou");
        enemy.setAlignment(Pos.BOTTOM_RIGHT);
        enemy.getChildren().addAll(enemyLabel, enemyBoard);
        return enemy;
        }

        private VBox createStartButtonBox() {
        VBox startButtonBox = new VBox(createStartButton());
        startButtonBox.getStyleClass().add("startButtonBox");
        return startButtonBox;
        }

        private HBox createBottomBox() {
        HBox bottomBox = new HBox(40, createStartButtonBox());
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        return bottomBox;
        }

        private HBox createBoards() {
        HBox boards = new HBox(50, createYou(), createEnemy());
        boards.setAlignment(Pos.CENTER);
        return boards;
        }

        private Button createStartButton() {
        Button startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        startButton.setOnAction(helloController.startButtonHandler());
        startButton.setAlignment(Pos.CENTER);
        return startButton;
        }
}