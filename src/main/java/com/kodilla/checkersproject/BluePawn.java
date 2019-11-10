package com.kodilla.checkersproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public final class BluePawn implements Pawn{


    @Override
    public String color() {
        return "blue";
    }

    @Override
    public Circle circle (int i, int j) {
        Circle c = new Circle(45, Color.BLUE);
        c.setCenterY(i);
        c.setCenterX(j);
        return c;
    }
}
