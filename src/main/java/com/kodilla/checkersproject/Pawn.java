package com.kodilla.checkersproject;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.kodilla.checkersproject.Checkers.FIELD_SIZE;

public class Pawn extends StackPane {

    private PawnTeam team;

    private PawnTeam nextPlayer = PawnTeam.RED;

    private boolean isRedTurn = true;


    private double mouseX, mouseY;
    private double oldX, oldY;

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

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

        Circle pawnShape = new Circle(FIELD_SIZE * 0.40);
        pawnShape.setFill(team == PawnTeam.RED ? Color.RED : Color.BLUE);

        pawnShape.setTranslateX((FIELD_SIZE - ((FIELD_SIZE * 0.40) * 2)) / 2);
        pawnShape.setTranslateY((FIELD_SIZE - ((FIELD_SIZE * 0.40) * 2)) / 2);

        getChildren().addAll(pawnShape);




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


