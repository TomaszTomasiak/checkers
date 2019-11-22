package com.kodilla.checkersproject;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field {

    private int x;
    private int y;
    private int width = 90;
    private int height = 90;
    private Pawn pawn;
    private Rectangle rectangle;

    private Rectangle rectangle(int i, int j) {

        x = width * i;
        y = height * j;

        Rectangle r = new Rectangle(x, y, width, height);
        r.setFill(Color.LIGHTYELLOW);
        r.setY(x);
        r.setY(y);
        return r;
    }

    @Override
    public String toString() {
        return "Field{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", pawn=" + pawn +
                ", rectangle=" + rectangle +
                '}';
    }

    public Field(int i, int j) {
        this.rectangle = rectangle(i, j);
        this.pawn = pawn;
        this.x = width + i;
        this.y = height + j;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
}

