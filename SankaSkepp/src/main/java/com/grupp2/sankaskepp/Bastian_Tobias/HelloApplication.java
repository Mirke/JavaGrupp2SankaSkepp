package com.grupp2.sankaskepp.Bastian_Tobias;

import com.grupp2.sankaskepp.Bastian_Tobias.ComputerAI;

import com.grupp2.sankaskepp.Bastian_Tobias.TheBattle;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.ControlOfInput;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


    private GameBoard youBoard, serverBoard;

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
        title.setPrefHeight(70);
        title.setAlignment(Pos.TOP_CENTER);

        title.getChildren().add(titleLabel);

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
                submarine.setPadding(new Insets(70,180,0,0));
                cruiser.setPadding(new Insets(78,80,0,0));
                battleship.setPadding(new Insets(405,80,0,0));
                carrier.setPadding(new Insets(90,120,0,0));
                boatBox.getChildren().addAll(submarine, cruiser, battleship, carrier);


        HBox history = new HBox();
        Text historyText = new Text("Test");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");


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

        /************************************************************************
         * TODO: svar från motståndaren: har vi träffat eller missat?
         * den informationen ska målas upp på motståndarens bräde (server)
         * behöver veta var den inforamtionen kommer ifrån
         * BYT NAMN: server ska bli enemy.
         **********************************************************************/
        // Tobias { ***********

        // you
        Boat youBoat = new Boat();
        PlaceBoats youPlaceBoats = new PlaceBoats();
        youBoat.createBoats();
        youPlaceBoats.initializeGridArray();
        youPlaceBoats.placeBoats(youBoat.getBoats());
        youBoard = new GameBoard(youBoat);
        ComputerAI youAI = new ComputerAI(youBoat);
        ControlOfInput youControlOfInput = new ControlOfInput(youBoard);

        // -------------------------------------------

        // Server
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        serverPlaceBoats.initializeGridArray();
        serverPlaceBoats.placeBoats(serverBoat.getBoats());
        serverBoard = new GameBoard();
        ComputerAI serverAI = new ComputerAI();
        ControlOfInput serverControlOfInput = new ControlOfInput(serverBoard);


        // klass där AI spelar mot varann
        // TheBattle theBattle = new TheBattle(enemyBoard,serverBoard,enemyAI,serverAI);

        // ********  } Tobias


        you.getChildren().addAll(youLabel, youBoard);
        enemy.getChildren().addAll(enemyLabel, serverBoard);


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

        HBox bottomBox = new HBox(40, startButtonBox);


        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, history, filler, boards, bottomBox);

        StackPane stack = new StackPane(root, boatBox);

        history.setAlignment(Pos.BASELINE_CENTER);
        boatBox.setAlignment(Pos.BASELINE_RIGHT);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);


        return stack;
        // hej

        // KONTROLLPANEL?
        //root.setRight(new TextArea("Kanske en kontrollpanel här som håller koll vilka kordinater " +
        //     "                   som lades och om träff eller miss"));
    }
}