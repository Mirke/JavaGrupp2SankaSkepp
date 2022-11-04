package com.grupp2.sankaskepp;

import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
import com.grupp2.sankaskepp.TestStart.ComputerAI;
import com.grupp2.sankaskepp.TestStart.TheBattle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {
    private boolean gameIsOn = false;
    private GameBoard enemyBoard, serverBoard;

    private Random random = new Random();


    public static void main(String[] args) {

        // startar fx
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 700);

        // Enemy
        Boat enemyBoat = new Boat();
        PlaceBoats enemyPlaceBoats = new PlaceBoats();
        enemyBoat.createBoats();
        enemyPlaceBoats.initializeGridArray();
        enemyPlaceBoats.placeBoats(enemyBoat.getBoats());
        enemyBoard = new GameBoard(enemyBoat);
        ComputerAI enemyAI = new ComputerAI(enemyBoat);

        // ***************************************

        // Server
        Boat serverBoat = new Boat();
        PlaceBoats serverPlaceBoats = new PlaceBoats();
        serverBoat.createBoats();
        serverPlaceBoats.initializeGridArray();
        serverPlaceBoats.placeBoats(serverBoat.getBoats());
        serverBoard = new GameBoard(serverBoat);
        ComputerAI serverAI = new ComputerAI(serverBoat);

        // klass där AI spelar mot varann
        TheBattle theBattle = new TheBattle(enemyBoard,serverBoard,enemyAI,serverAI);

        VBox vBox = new VBox(10, enemyBoard, serverBoard);
        vBox.setAlignment(Pos.TOP_LEFT);

        root.setCenter(vBox);

        return root;
    }
}