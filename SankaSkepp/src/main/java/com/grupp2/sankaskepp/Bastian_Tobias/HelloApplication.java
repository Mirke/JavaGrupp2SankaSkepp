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


        HBox history = new HBox();
        //history.setPadding(new Insets(0,0,40,0));
        Text historyText = new Text("Test");
        historyText.setFill(Color.web("37a8b7"));
        historyText.setEffect(dropShadow);
        history.getChildren().add(historyText);
        history.getStyleClass().add("history");

        Region filler = new Region();
        filler.setPrefHeight(50);
        HBox.setHgrow(filler, Priority.ALWAYS);




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
        //stopButton.setAlignment(Pos.CENTER);

        HBox bottomBox = new HBox(40, startButtonBox);



        HBox boards = new HBox(50, you, enemy);
        boards.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, history, filler, boards, bottomBox, submarine, cruiser);
        history.setAlignment(Pos.BASELINE_CENTER);
        submarine.setAlignment(Pos.TOP_RIGHT);

        //submarine.setAlignment(Pos.TOP_RIGHT);



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