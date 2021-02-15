package com.kodilla.checkersproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Checkers extends Application {

    public static final int FIELD_SIZE = 90;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public boolean gameOver = false;

    private PawnTeam nextPlayer = PawnTeam.RED;

    private final Group fieldGroup = new Group();
    private final Group pawnGroup = new Group();
    private final Group buttons = new Group();
    private final Group labels = new Group();

    private List<Pawn> redPawnList = new ArrayList<Pawn>();
    private List<Pawn> bluePawnList = new ArrayList<Pawn>();

    private final Field[][] fields = new Field[WIDTH][HEIGHT];

    private final Label turnStatus = new Label();
    private final Label theWinner = new Label();
    private final Label redStatus = new Label();
    private final Label blueStatus = new Label();


    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * FIELD_SIZE, HEIGHT * FIELD_SIZE);
        root.getChildren().addAll(fieldGroup, pawnGroup, buttons, labels);

        setBoard();

        turnStatus.setTextFill(Color.RED);
        turnStatus.setFont(new Font("Arial", 20));
        turnStatus.setLayoutX(160);
        turnStatus.setLayoutY(737);
        turnStatus.setText(gameStatus());

        theWinner.setTextFill(Color.BLACK);
        theWinner.setFont(new Font("Arial", 20));
        theWinner.setLayoutX(160);
        theWinner.setLayoutY(770);

        redStatus.setTextFill(Color.BLACK);
        redStatus.setFont(new Font("Arial", 15));
        redStatus.setLayoutX(500);
        redStatus.setLayoutY(750);
        redStatus.setText(redResult());

        blueStatus.setTextFill(Color.BLACK);
        blueStatus.setFont(new Font("Arial", 15));
        blueStatus.setLayoutX(500);
        blueStatus.setLayoutY(780);
        blueStatus.setText(blueResult());

        Button gameReset = new Button();
        gameReset.setText("Start new game");
        gameReset.setLayoutX(30);
        gameReset.setLayoutY(737);
        gameReset.setPrefSize(100, 25);
        gameReset.toFront();

        gameReset.setOnAction(e -> {

            cleanTheBoard();
            setBoard();
            nextPlayer = PawnTeam.RED;
            turnStatus.setText(gameStatus());
            redStatus.setText(redResult());
            blueStatus.setText(blueResult());
            theWinner.setText("");
            gameOver = false;

        });

        Button gameFinish = new Button();
        gameFinish.setText("Finish the game");
        gameFinish.setLayoutX(30);
        gameFinish.setLayoutY(773);
        gameFinish.setPrefSize(100, 25);
        gameFinish.toFront();

        gameFinish.setOnAction(e -> {
            if (!gameOver) {
                nextPlayer = null;
                turnStatus.setText(gameOverResult());
            }
        });

        buttons.getChildren().addAll(gameFinish, gameReset);
        labels.getChildren().addAll(turnStatus, theWinner, redStatus, blueStatus);

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContent(), 720, 820);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setBoard() {

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Field field = new Field((x + y) % 2 == 0, x, y);
                fields[x][y] = field;
                fieldGroup.getChildren().add(field);

                Pawn pawn = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    pawn = pawnCreator(PawnTeam.BLUE, x, y);
                    redPawnList.add(pawn);
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    pawn = pawnCreator(PawnTeam.RED, x, y);
                    bluePawnList.add(pawn);
                }
                if (pawn != null) {
                    field.setPawn(pawn);
                    pawnGroup.getChildren().add(pawn);
                }
            }
        }
    }

    private MoveResult tryMove(Pawn pawn, int newX, int newY) {
        if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {

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

                pawn.setMouseX(e.getSceneX());
                pawn.setMouseY(e.getSceneY());
                pawn.toFront();

            } else {
                e.consume();
            }

        });

        pawn.setOnMouseDragged(e -> {
            if (pawn.getTeam() == nextPlayer) {
                pawn.relocate(e.getSceneX() - pawn.getMouseX() + pawn.getOldX(), e.getSceneY() - pawn.getMouseY() + pawn.getOldY());
                pawn.toFront();

            } else {
                e.consume();
            }
        });

        pawn.setOnMouseReleased(e -> {
            if (pawn.getTeam() == nextPlayer) {
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

                        if (newY == 7 && pawn.getTeam().equals(PawnTeam.BLUE)) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.BLUE);
                            theWinner.setText("BLUE IS THE WINNER");
                            theWinner.setTextFill(Color.BLUE);
                            gameOver = true;

                        } else if (newY == 0 && pawn.getTeam().equals(PawnTeam.RED)) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.RED);
                            theWinner.setText("RED IS THE WINNER");
                            theWinner.setTextFill(Color.RED);
                            gameOver = true;

                        } else {
                            turnChange();
                            turnStatus.setText(gameStatus());
                        }

                        break;

                    case KILL:
                        pawn.move(newX, newY);
                        fields[x0][y0].setPawn(null);
                        fields[newX][newY].setPawn(pawn);

                        Pawn competitorPiece = result.getPawn();
                        fields[toBoard(competitorPiece.getOldX())][toBoard(competitorPiece.getOldY())].setPawn(null);
                        pawnGroup.getChildren().remove(competitorPiece);
                        if (competitorPiece.getTeam().equals(PawnTeam.RED)) {
                            redPawnList.remove(redPawnList.get(0));
                        } else if (competitorPiece.getTeam().equals(PawnTeam.BLUE)) {
                            bluePawnList.remove(bluePawnList.get(0));
                        }
                        if (newY == 7 && pawn.getTeam().equals(PawnTeam.BLUE)) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.BLUE);
                            theWinner.setText("BLUE IS THE WINNER");
                            theWinner.setTextFill(Color.BLUE);
                            gameOver = true;

                        } else if (newY == 0 && pawn.getTeam().equals(PawnTeam.RED)) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.RED);
                            theWinner.setText("RED IS THE WINNER");
                            theWinner.setTextFill(Color.RED);
                            gameOver = true;

                        } else if (bluePawnList.size() == 0) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.RED);
                            theWinner.setText("RED IS THE WINNER");
                            theWinner.setTextFill(Color.RED);

                            gameOver = true;

                        } else if (redPawnList.size() == 0) {

                            nextPlayer = null;
                            turnStatus.setText("GAME OVER");
                            turnStatus.setTextFill(Color.BLUE);
                            theWinner.setText("BLUE IS THE WINNER");
                            theWinner.setTextFill(Color.BLUE);

                            gameOver = true;

                        } else {
                            turnChange();
                            turnStatus.setText(gameStatus());
                            redStatus.setText(redResult());
                            blueStatus.setText(blueResult());
                        }
                        break;
                }
            } else {
                e.consume();
            }
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

    public void cleanTheBoard() {

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                Pawn pawnToClean = fields[x][y].getPawn();
                pawnGroup.getChildren().remove(pawnToClean);

                Field fieldToClean = fields[x][y];
                fieldGroup.getChildren().remove(fieldToClean);
            }

            redPawnList = new ArrayList<>();
            bluePawnList = new ArrayList<>();
        }

    }

    public String gameStatus() {

        String text = "";

        if (nextPlayer == PawnTeam.RED) {
            text = "RED TURN";
            turnStatus.setTextFill(Color.RED);

        } else if (nextPlayer == PawnTeam.BLUE) {
            text = "BLUE TURN";
            turnStatus.setTextFill(Color.BLUE);
        }
        return text;
    }

    public String redResult() {
        String text = "RED: " + redPawnList.size() + "/12 pawns remaining";

        return text;
    }

    public String blueResult() {
        String text = "BLUE: " + bluePawnList.size() + "/12 pawns remaining";

        return text;
    }

    public String gameOverResult() {
        String result = "";

        if (redPawnList.size() > bluePawnList.size()) {
            result = "GAME OVER";
            turnStatus.setTextFill(Color.RED);
            theWinner.setText("RED IS THE WINNER");
            theWinner.setTextFill(Color.RED);


        } else if (redPawnList.size() < bluePawnList.size()) {
            result = "GAME OVER";
            turnStatus.setTextFill(Color.BLUE);
            theWinner.setText("BLUE IS THE WINNER");
            theWinner.setTextFill(Color.BLUE);
        } else {
            result = "GAME OVER - DRAW";
            turnStatus.setTextFill(Color.BLACK);
        }

        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

