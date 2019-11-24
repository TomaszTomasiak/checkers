package com.kodilla.checkersproject;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.kodilla.checkersproject.Checkers.FIELD_SIZE;

public class Pawn extends StackPane {

    private PawnTeam team;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public PawnTeam getTeam() {
        return team;
    }

    public Pawn(PawnTeam team, int x, int y) {
        this.team = team;

        move(x, y);

        Circle pieceShape = new Circle(FIELD_SIZE * 0.40);
        pieceShape.setFill(team == PawnTeam.RED ? Color.RED : Color.BLUE);

        pieceShape.setTranslateX((FIELD_SIZE - ((FIELD_SIZE * 0.40) * 2)) / 2);
        pieceShape.setTranslateY((FIELD_SIZE - ((FIELD_SIZE * 0.40) * 2)) / 2);

        getChildren().addAll(pieceShape);


        setOnMousePressed(e -> {

            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            toFront();

        });

        setOnMouseDragged(e -> {

            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
            toFront();
            setCursor(Cursor.HAND);


        });
    }

    public void move(int x, int y) {
        oldX = x * FIELD_SIZE;
        oldY = y * FIELD_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}