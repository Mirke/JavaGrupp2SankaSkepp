package com.grupp2.sankaskepp;
import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;
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
    private GameBoard attackerBoard, defenderBoard;

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


        Boat boat = new Boat();
        PlaceBoats placeBoats = new PlaceBoats();
        boat.createBoats();
        placeBoats.initializeGridArray();
        placeBoats.placeBoats(boat.getBoats());

        attackerBoard = new GameBoard(boat);

        // ***************************************

        Boat boat2 = new Boat();
        PlaceBoats placeBoats2 = new PlaceBoats();
        boat2.createBoats();
        placeBoats2.initializeGridArray();
        placeBoats2.placeBoats(boat2.getBoats());

        defenderBoard = new GameBoard(boat2);

        VBox vBox = new VBox(10, attackerBoard, defenderBoard);
        vBox.setAlignment(Pos.TOP_LEFT);

        root.setCenter(vBox);

        return root;
    }
}