package com.kodilla.checkersproject;

public enum PawnTeam {
    RED(-1), BLUE(1);

    final int teamMove;

    PawnTeam(int teamMove) {
        this.teamMove = teamMove;
    }
}
