package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {

    private Image board = new Image("file:src/main/resources/checkersboard.png");
    private FlowPane chekersBoard = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridBoard = new GridPane();
        gridBoard.setAlignment(Pos.TOP_LEFT);

        ImageView img = new ImageView(board);
        img.setImage(board);

        chekersBoard.getChildren().add(img);

        gridBoard.add(chekersBoard, 0, 0, 8, 8);

        Field objField = new Field();
        Field[][] fields = new Field[8][8];


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gridBoard.add(objField.rectangle(i, j), i, j);
                fields[i][j] = new Field(i, j);
            }
        }
        BluePawn objBlue = new BluePawn();
        RedPawn objRed = new RedPawn();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i + j) % 2 == 0) {

                    gridBoard.add(objBlue.circle(i, j), i, j);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 5; j < 8; j++) {
                if ((i + j) % 2 == 0) {

                    gridBoard.add(objRed.circle(i, j), i, j);
                }
            }
        }
        Scene scene = new Scene(gridBoard, 1000, 720, Color.DARKGREEN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
