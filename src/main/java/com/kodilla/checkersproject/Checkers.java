package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Checkers extends Application {

    public static final int FIELD_SIZE = 90;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final int LABELS_PLACE = 280;

    private PawnTeam nextPlayer = PawnTeam.RED;

    private Group fieldGroup = new Group();
    private Group pawnGroup = new Group();


    private Field[][] fields = new Field[WIDTH][HEIGHT];


    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * FIELD_SIZE, HEIGHT * FIELD_SIZE);
        root.getChildren().addAll(fieldGroup, pawnGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Field field = new Field((x + y) % 2 == 0, x, y);
                fields[x][y] = field;
                fieldGroup.getChildren().add(field);

                Pawn pawn = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    pawn = pawnCreator(PawnTeam.BLUE, x, y);

                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    pawn = pawnCreator(PawnTeam.RED, x, y);

                }
                if (pawn != null) {
                    field.setPawn(pawn);
                    pawnGroup.getChildren().add(pawn);
                }
            }
        }


        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContent(), 1000, 720, Color.DARKGREEN);


        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private MoveResult tryMove(Pawn pawn, int newX, int newY) {


            if (fields[newX][newY].hasPawn() || (newX + newY) % 2 == 0) {
                return new MoveResult(MoveType.NONE);
            }


        int x0 = toBoard(pawn.getOldX());
        int y0 = toBoard(pawn.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == pawn.getTeam().teamMove) {

            return new MoveResult(MoveType.NORMAL);

        } else if (Math.abs(newX - x0) == 2 && newY - y0 == pawn.getTeam().teamMove * 2) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;


                if (fields[x1][y1].hasPawn() && fields[x1][y1].getPawn().getTeam() != pawn.getTeam()) {
                    return new MoveResult(MoveType.KILL, fields[x1][y1].getPawn());
                }
            }
                return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int) (pixel + FIELD_SIZE / 2) / FIELD_SIZE;
    }

    public Pawn pawnCreator(PawnTeam type, int x, int y) {

        Pawn pawn = new Pawn(type, x, y);
        pawn.setOnMousePressed(e -> {
            if (pawn.getTeam() == nextPlayer) {

                //mouseX = e.getSceneX();
                //mouseY = e.getSceneY();
                pawn.toFront();
            } else {
                e.consume();
            }

        });

        pawn.setOnMouseDragged(e -> {

            pawn.relocate(e.getSceneX() - pawn.getMouseX() + pawn.getOldX(), e.getSceneY() - pawn.getMouseY() + pawn.getOldY());
            pawn.toFront();
            pawn.setCursor(Cursor.HAND);
        });
        pawn.setOnMouseReleased(e -> {
            int newX = toBoard(pawn.getLayoutX());
            int newY = toBoard(pawn.getLayoutY());

            MoveResult result = tryMove(pawn, newX, newY);


            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());


            switch (result.getMoveType()) {
                case NONE:
                    pawn.abortMove();
                    break;

                case NORMAL:
                    pawn.move(newX, newY);
                    fields[x0][y0].setPawn(null);
                    fields[newX][newY].setPawn(pawn);

                    break;

                case KILL:
                    pawn.move(newX, newY);
                    fields[x0][y0].setPawn(null);
                    fields[newX][newY].setPawn(pawn);

                    Pawn competitorPiece = result.getPawn();
                    fields[toBoard(competitorPiece.getOldX())][toBoard(competitorPiece.getOldY())].setPawn(null);
                    pawnGroup.getChildren().remove(competitorPiece);


                    break;
            }
            turnChange();
        });

        return pawn;
    }

    public void turnChange() {
        if (nextPlayer == PawnTeam.RED) {
            nextPlayer = PawnTeam.BLUE;

        } else {
            nextPlayer = PawnTeam.RED;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}

