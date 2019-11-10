package com.kodilla.checkersproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field {

    public int x;
    public int y;
    public int width = 90;
    public int height = 90;

    public Rectangle rectangle(int i, int j) {

        x = width * i;
        y = height * j;

       Rectangle r = new Rectangle(x, y , width, height);
        r.setFill(Color.TRANSPARENT);
        r.setY(x);
        r.setY(y);
        return r;
    }
    public Field(){
    }

    public Field(int i, int j) {
        x = width + i;
        y = height + j;
    }
}

