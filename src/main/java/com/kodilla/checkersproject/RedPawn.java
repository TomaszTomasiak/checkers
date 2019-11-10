package com.kodilla.checkersproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public final class RedPawn implements Pawn {

    @Override
    public String color() {
        return "red";
    }

    @Override
        public Circle circle (int i, int j) {
            Circle c = new Circle(45, Color.RED);
            c.setCenterY(i);
            c.setCenterX(j);
            return c;
    }
}
