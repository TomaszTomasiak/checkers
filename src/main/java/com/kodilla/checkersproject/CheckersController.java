package com.kodilla.checkersproject;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CheckersController extends Pawn {


    Pawn pawn;
    Field field;


    private String reds = "REDS TURN";
    private String blues = "BLUES TURN";
    private Boolean playerTurn = false;

    // czerwone zaczynają więc wartości przypisane są dla czerwonych;
    int xMove;
    int yMove;


    public CheckersController(int i, int j, Color color) {
        super(i, j, color);
    }





/*
Legenda:
    0 - oznaczenie pola pustego
    1 - oznaczenie pionka czerwonego
    2 - oznaczenie pionka niebieskiego
    3 - oznaczenie wybranego pionka czerwonego (wybrany, czyli na którym ustawiony jest kursor myszki przez gracza)
    4 - oznaczenie wybranego pionka niebieskiego (wybrany, czyli na którym ustawiony jest kursor myszki przez gracza)
    5 - oznaczenie pola pustego, na które można wykonać ruch (przesunąć pionka)

 */

    public void move() {


        while (true) {

            if (pawn.getColor().equals(Color.RED)) {
                xMove = -1;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                    }
                }

            } else {

            }

        }

    }


    /*
     * Scans CheckerBoardss grid for valid moves within a radius of 1
     * @param  board  the CheckerBoard instance to be scanned
     * @param  checker  the Checker which the scan originates from
     * @return all valid moves relative to the checker passed to the scanner

     */

    /*
    public static Rectangle potentialMoveTo() {
Rectangle tmp = new Rectangle();
        //[lowerBounds, upperBounds)
        int upperBounds = 2;
        int lowerBounds = -1;

        int i = tmp.get

        int j = board.getRowIndex(checker);
        List<Point2D> toAdd = new ArrayList<Point2D>();


            if(checker.team == Checker.Team.RED) {
                lowerBounds = 1;
            }else{
                upperBounds = 0;
            }

        for(int dY=lowerBounds; dY<upperBounds; dY+=2) {

            if(j+dY < 0) {
                continue;
            } else if(j+dY > 7) {
                break;
            }

            for(int dX=-1; dX<2; dX+=2) {

                if(i+dX < 0) continue;
                else if(i+dX > 7) break;
                if(board.getCell(Checker.class, i+dX, j+dY) == null) toAdd.add(new Point2D(i+dX, j+dY));
            }
        }
        return toAdd.isEmpty() ? null : new PointCB(checker, toAdd.toArray(new Point2D[0]));
    }

     */

    /**
     * Scans CheckerBoardss grid for valid moves within a radius of 2
     * @param  board  the CheckerBoard instance to be scanned
     * @param  checker  the Checker which the scan originates from
     * @return all valid moves relative to the checker passed to the scanner
     */
    /*
    public static PointCB scanR2(CheckerBoard board, Checker checker) {

        //[lowerBounds, upperBounds)
        int upperBounds = 3;
        int lowerBounds = -2;
        int i = board.getColumnIndex(checker);
        int j = board.getRowIndex(checker);
        List<Point2D> toAdd = new ArrayList<Point2D>();
        Checker.Team lookFor = board.getTurn() == Checker.Team.RED ? Checker.Team.BLACK : Checker.Team.RED;

        if(!checker.isKing()) {
            if(checker.team == Checker.Team.RED) lowerBounds = 2;
            else upperBounds = 0;
        }
        for(int dY=lowerBounds; dY<upperBounds; dY+=4) {

            if(j+dY < 0) continue;
            else if(j+dY > 7) break;

            for(int dX=-2; dX<3; dX+=4) {

                if(i+dX < 0) continue;
                else if(i+dX > 7) break;
                if(board.getCell(Checker.class, i+dX/2, j+dY/2) != null) {
                    if(board.getCell(Checker.class, i+dX/2, j+dY/2).get(0).team == lookFor) {
                        if(board.getCell(Checker.class, i+dX, j+dY) == null) toAdd.add(new Point2D(i+dX, j+dY));
                    }
                }
            }
        }
        return toAdd.isEmpty() ? null : new PointCB(checker, toAdd.toArray(new Point2D[0]));
    }
}

     */


}
