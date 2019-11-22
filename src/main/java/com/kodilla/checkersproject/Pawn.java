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
        circle.setCenterY((j*90)+45);
        circle.setCenterX((i*90)+45);
        this.i = i;
        this.j = j;
        this.color = color;
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

    @Override
    public String toString() {
        return "Pawn{" +
                "i=" + i +
                ", j=" + j +
                ", circle=" + circle +
                ", color=" + color +
                '}';
    }
}
/*
public final int x;
	public final int y;

	// C'tor
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "{ \"x\": " + x + ", \"y\": " + y + " }";
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is also a point, and the x and y are the same, it is equal.
		return obj instanceof Point && ((Point)obj).x == this.x && ((Point)obj).y == this.y;
	}
 */