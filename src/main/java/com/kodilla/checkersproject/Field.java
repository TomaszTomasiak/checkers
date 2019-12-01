package com.kodilla.checkersproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends Rectangle {

    private Pawn pawn;

    public boolean hasPawn() {
        return pawn != null;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }


    public Field(boolean light, int x, int y) {
        setWidth(Checkers.FIELD_SIZE);
        setHeight(Checkers.FIELD_SIZE);

        relocate(x * Checkers.FIELD_SIZE, y * Checkers.FIELD_SIZE);
        setFill(light ? Color.BLACK : Color.WHITE);

    }


}

