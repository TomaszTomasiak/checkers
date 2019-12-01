package com.kodilla.checkersproject;

public class MoveResult {
    private MoveType moveType;
    private Pawn pawn;

    public MoveType getMoveType() {
        return moveType;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public MoveResult(MoveType moveType) {
        this(moveType, null);
    }

    public MoveResult(MoveType moveType, Pawn pawn) {
        this.moveType = moveType;
        this.pawn = pawn;
    }
}
