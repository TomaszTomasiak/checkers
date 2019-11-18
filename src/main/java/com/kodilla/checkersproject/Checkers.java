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
import java.util.List;
import java.util.Optional;

public class Checkers extends Application {

    ArrayList<Pawn> theField;
    ArrayList<Pawn> redsList = new ArrayList<>();
    ArrayList<Pawn> bluesList = new ArrayList<>();

    ListView<Circle> sourceView = new ListView<>();
    ListView<Circle> targetView = new ListView<>();

    Optional<Pawn> fieldWithPawn;

    Circle sourcePawn = new Circle();
    Circle targetPawn = new Circle();

    Boolean redPlayerTurn;
    Boolean bluePlayerTurn;

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    private Image board = new Image("file:src/main/resources/checkersboard.png");
    private FlowPane chekersBoard = new FlowPane(Orientation.HORIZONTAL);

    static final DataFormat PAWN = new DataFormat("Pawn");

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
        Pawn pawn = new Pawn();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field objField = new Field(i, j);

                    gridBoard.add(objField.getRectangle(), i, j);
                    theField = new ArrayList<>();
                    fields[i][j] = new Field(i, j);



                if ((i < 8 && j < 3) && ((i + j) % 2 == 0)) {

                    Circle bluePawn = new Circle(45, Color.BLUE);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn, i, j);
                    theField.add(pawn);
                    bluesList.add(pawn);
                    //sourceView.getItems().add(pawn);
                    bluePawn.setCursor(Cursor.HAND);
                    bluePawn.setOnMousePressed(circleOnMousePressedEventHandler);
                    bluePawn.setOnMouseDragged(circleOnMouseDraggedEventHandler);
                }

                if ((i < 8 && j >= 5) && ((i + j) % 2 == 0)) {
                    Circle redPawn = new Circle(45, Color.RED);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn, i, j);
                    theField.add(pawn);
                    redsList.add(pawn);
                    redPawn.setCursor(Cursor.HAND);
                    redPawn.setOnMousePressed(circleOnMousePressedEventHandler);
                    redPawn.setOnMouseDragged(circleOnMouseDraggedEventHandler);
                }
            }
        }

        Scene scene = new Scene(gridBoard, 1000, 720, Color.DARKGREEN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                }
            };
}

