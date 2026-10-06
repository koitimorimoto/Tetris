package com.example.tetris.model;

public class GameModel {

    private Board board;
    private Piece currentPiece;

    public GameModel() {
        reset();
    }

    public void reset() {
        board = new Board();
        currentPiece = new Piece(PieceType.T, 3, 0);
    }

    public boolean movePiece(int dx, int dy) {
        int nextX = currentPiece.getX() + dx;
        int nextY = currentPiece.getY() + dy;
        if (!board.canPlace(currentPiece.getShape(), nextX, nextY)) {
            return false;
        }
        currentPiece.move(dx, dy);
        return true;
    }

    public boolean rotatePiece() {
        boolean[][] rotatedShape = currentPiece.getRotatedShape();
        if (!board.canPlace(rotatedShape, currentPiece.getX(), currentPiece.getY())) {
            return false;
        }
        currentPiece.setShape(rotatedShape);
        return true;
    }

    public void dropPiece() {
        while (movePiece(0, 1)) {
        }
    }

    public boolean[][] getVisibleCells() {
        return board.getVisibleCells(currentPiece);
    }
}
