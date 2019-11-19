package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class Checkers extends Application {

    ArrayList<Pawn> theField;
    ArrayList<Pawn> redsList = new ArrayList<>();
    ArrayList<Pawn> bluesList = new ArrayList<>();

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

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

        Group root = new Group();

        Field[][] fields = new Field[8][8];


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field objField = new Field(i, j);

                gridBoard.add(objField.getRectangle(), i, j);
                theField = new ArrayList<>();
                fields[i][j] = new Field(i, j);


                if ((i < 8 && j < 3) && ((i + j) % 2 == 0)) {
                    Pawn pawn = new Pawn(i, j, Color.BLUE);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    //theField.add(pawn);
                    //bluesList.add(pawn);
                    pawn.setCursor(Cursor.HAND);
                    pawn.setOnMousePressed(pawnOnMousePressedEventHandler);
                    pawn.setOnMouseDragged(pawnOnMouseDraggedEventHandler);
                }

                if ((i < 8 && j >= 5) && ((i + j) % 2 == 0)) {
                    Pawn pawn = new Pawn(i, j, Color.RED);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    theField.add(pawn);
                    redsList.add(pawn);
                    pawn.setCursor(Cursor.HAND);
                    pawn.setOnMousePressed(pawnOnMousePressedEventHandler);
                    pawn.setOnMouseDragged(pawnOnMouseDraggedEventHandler);
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
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Pawn) (t.getSource())).getTranslateX();
                    orgTranslateY = ((Pawn) (t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> pawnOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Pawn) (t.getSource())).setTranslateX(newTranslateX);
                    ((Pawn) (t.getSource())).setTranslateY(newTranslateY);
                }
            };
}

