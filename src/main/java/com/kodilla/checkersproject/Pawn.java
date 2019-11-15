package com.kodilla.checkersproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle{
    private int i;
    private int j;
    private Circle circle;
    private Color color;

    public Pawn(int i, int j, Color color) {
        this.circle = new Circle(45, color);
        circle.setCenterY(i+45);
        circle.setCenterX(j+45);
        this.i = i;
        this.j = j;
        this.color = color;
    }

    public Pawn() {

    }

    public Circle getCircle() {
        return circle;
    }

    public Color getColor() {
        return color;
    }

    public Pawn getPawn(){
        return this;
    }
}
