package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Optional;

public class Checkers extends Application {

    ArrayList<Pawn> theField;

    Optional<Pawn> fieldWithPawn;

    Pawn sourcePawn = new Pawn();
    Pawn targetPawn = new Pawn();

    private Image board = new Image("file:src/main/resources/checkersboard.png");
    private FlowPane chekersBoard = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    static final DataFormat PAWN = new DataFormat("Pawn");



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
                    theField = new ArrayList<>();
                    fields[i][j] = new Field(i, j);
                }

                if ((i < 8 && j < 3) && ((i + j) % 2 == 0)) {

                    Pawn pawn = new Pawn(i, j, Color.BLUE);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    theField.add(pawn);
                }

                if ((i < 8 && j >= 5) && ((i + j) % 2 == 0)) {
                    Pawn pawn = new Pawn(i, j, Color.RED);
                    objField.setPawn(pawn);
                    gridBoard.add(pawn.getCircle(), i, j);
                    theField.add(pawn);
                }
            }
        }

        Scene scene = new Scene(gridBoard, 1000, 720, Color.DARKGREEN);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();


        // Add mouse event handlers for the source
        sourcePawn.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dragDetected(event);
            }
        });
        sourcePawn.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragDone(event);
            }
        });

        // Add mouse event handlers for the target
        targetPawn.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragOver(event);
            }
        });

        targetPawn.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragDropped(event);
            }
        });

    }
        private void dragDetected (MouseEvent event) {
            // User can drag only when there is text in the source field
            Pawn thePawn = sourcePawn.getPawn();

            if (thePawn == null) {
                event.consume();
                return;
            }

            // Initiate a drag-and-drop gesture
            Dragboard dragboard = sourcePawn.startDragAndDrop(TransferMode.COPY_OR_MOVE);

            // Add the source text to the Dragboard
            ClipboardContent content = new ClipboardContent();
            content.put(PAWN, sourcePawn);
            dragboard.setContent(content);
            event.consume();
        }

        private void dragOver (DragEvent event)
        {
            // If drag board has a string, let the event know that
            // the target accepts copy and move transfer modes
            Dragboard dragboard = event.getDragboard();

            if (dragboard.hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        }

        private void dragDropped (DragEvent event)
        {
            // Transfer the data to the target
            Dragboard dragboard = event.getDragboard();

            if (theField.isEmpty()) {
                theField.add(targetPawn);

                // Data transfer is successful
                event.setDropCompleted(true);
            } else {
                // Data transfer is not successful
                event.setDropCompleted(false);
            }

            event.consume();
        }

        private void dragDone (DragEvent event)
        {
            // Check how data was transfered to the target. If it was moved, clear the text in the source.
            TransferMode modeUsed = event.getTransferMode();

            if (modeUsed == TransferMode.MOVE) {
                // usuń pionek z pola
                theField.remove(sourcePawn);

            }

            event.consume();
        }


    }

