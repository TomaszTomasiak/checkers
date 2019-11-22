package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


public class Checkers extends Application {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    Circle selectedPawn;
    Circle posibleTargetPosition;
    Circle targetPosition;

    private Image board = new Image("file:src/main/resources/checkersboard.png");
    private FlowPane chekersBoard = new FlowPane(Orientation.HORIZONTAL);
// tablica dwuwymiarowa przechowująca współrzędne pionków

    private static int pawnsTab[][] = new int[8][8];
    ;

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

        Group root = new Group();

        Field[][] fields = new Field[8][8];


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field objField = new Field(i, j);

                if ((i + j) % 2 == 0) {
                    gridBoard.add(objField.getRectangle(), i, j);
                    fields[i][j] = new Field(i, j);
                    pawnsTab[i][j] = 0;
                    objField.getRectangle().setOnMouseClicked(pawnOnTheFiledPressedEventHandler);
                }

                if ((i < 8 && j < 3) && ((i + j) % 2 == 0)) {
                    Pawn pawn = new Pawn(i, j, Color.BLUE);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    pawnsTab[i][j] = 2;
                    pawn.getCircle().setCursor(Cursor.HAND);
                    pawn.getCircle().setOnMouseClicked(pawnOnMousePressedEventHandler);
                    pawn.getCircle().setOnMouseReleased(pawnOnTargetEventHandler);

                }

                if ((i < 8 && j >= 5) && ((i + j) % 2 == 0)) {
                    Pawn pawn = new Pawn(i, j, Color.RED);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    pawnsTab[i][j] = 1;
                    pawn.getCircle().setCursor(Cursor.HAND);
                    pawn.getCircle().setOnMouseClicked(pawnOnMousePressedEventHandler);
                }
            }
        }


        Scene scene = new Scene(gridBoard, 1000, 720, Color.DARKGREEN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    EventHandler<MouseEvent> pawnOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    selectedPawn = (Circle) (t.getSource());
                    selectedPawn.toFront();

                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();


                    int x;
                    int y;
                    x = (int) orgSceneX;
                    y = (int) orgSceneY;


                    double posX = selectedPawn.getCenterX() - 90.0;
                    double posY = selectedPawn.getCenterY() - 90.0;


                    targetPosition.setCenterX(posX);
                    targetPosition.setCenterY(posY);
                    targetPosition.setFill(Color.GREY);


                    selectedPawn.setFill(Color.VIOLET);


                    System.out.println(selectedPawn);
                    System.out.println("x: " + x);
                    System.out.println("y: " + y);


                }
            };
/*
    EventHandler<MouseEvent> pawnOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                    ((Circle) (t.getSource())).setTranslateY(newTranslateY);

                    System.out.println("offsetX : " + offsetX);
                    System.out.println("offsetY: " + offsetY);


                }
            };

 */


    EventHandler<MouseEvent> pawnOnTheFiledPressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {


                }
            };

    EventHandler<MouseEvent> pawnOnTargetEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {


                }
            };

    public void posibleTargetField() {

    }
}

