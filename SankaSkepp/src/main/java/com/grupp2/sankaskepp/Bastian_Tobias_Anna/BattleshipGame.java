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
    private final DropShadow dropShadow = new DropShadow();
    private HelloController helloController;

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
        VBox root = new VBox();
        root.setPrefSize(1200, 760);
        root.getChildren().addAll(createTitle(), createHistory(), createFiller(), createBoard(), createBottomBox());
        StackPane stack = new StackPane(buildBoxWithBoats(), root);

        return stack;
    }

    public HBox createBoard(){
        DropShadow dropShadow = new DropShadow();
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
        this.enemyBoard = helloController.enemyBoard;
        this.youBoard = helloController.youBoard;
        you.getChildren().addAll(youLabel, youBoard);
        enemy.getChildren().addAll(enemyLabel, enemyBoard);
        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);

        return boards;
    }

    public HBox createHistory(){
        HBox history = new HBox();
        Text historyText = new Text("History");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        helloController = new HelloController(historyText);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");
        history.setAlignment(Pos.BASELINE_CENTER);

        return history;
    }

    public HBox createBottomBox(){
        Button startButton = new Button("Start");
        startButton.setEffect(dropShadow);
        startButton.setOnAction(helloController.startButtonHandler());
        VBox startButtonBox = new VBox(startButton);
        startButtonBox.getStyleClass().add("startButtonBox");
        startButton.setAlignment(Pos.CENTER);
        HBox bottomBox = new HBox(40, startButtonBox);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);

        return bottomBox;
    }

    public Region createFiller(){
        Region filler = new Region();
        filler.setPrefHeight(50);
        HBox.setHgrow(filler, Priority.ALWAYS);

        return filler;
    }

    public HBox createTitle(){
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

    public VBox buildBoxWithBoats(){
        VBox boatBoxOut = new VBox();
        boatBoxOut.setAlignment(Pos.BASELINE_RIGHT);
        HBox submarine = new HBox();
        submarine.setAlignment(Pos.TOP_RIGHT);
        Text submarineLabel = new Text("4 submarines");
        defaultBoatStyle(submarineLabel,true);
        submarine.getChildren().add(submarineLabel);
        HBox cruiser = new HBox();
        cruiser.setAlignment(Pos.TOP_RIGHT);
        Text cruiserLabel = new Text("3 cruisers");
        defaultBoatStyle(cruiserLabel,true);
        cruiser.getChildren().add(cruiserLabel);
        HBox battleship = new HBox();
        battleship.setAlignment(Pos.TOP_RIGHT);
        Text battleshipLabel = new Text("2 battleships");
        defaultBoatStyle(battleshipLabel,true);
        battleship.getChildren().add(battleshipLabel);
        HBox carrier = new HBox();
        carrier.setAlignment(Pos.BOTTOM_RIGHT);
        Text carrierLabel = new Text("1 carrier");
        defaultBoatStyle(carrierLabel,true);
        carrier.getChildren().add(carrierLabel);
        submarine.setPadding(new Insets(70,180,0,0));
        cruiser.setPadding(new Insets(78,80,0,0));
        battleship.setPadding(new Insets(405,80,0,0));
        carrier.setPadding(new Insets(90,120,0,0));
        boatBoxOut.getChildren().addAll(submarine, cruiser, battleship, carrier);

        return boatBoxOut;
    }

    public void defaultBoatStyle(Text text, Boolean giveShadows){
        text.setFill(Color.web("37a8b7"));
        if(giveShadows){text.setEffect(dropShadow);}
    }
}