package com.example.tetris.model;

public class Board {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;

    private final boolean[][] occupiedCells = new boolean[HEIGHT][WIDTH];

    public boolean canPlace(boolean[][] shape, int pieceX, int pieceY) {
        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                if (!shape[row][column]) {
                    continue;
                }

                int boardX = pieceX + column;
                int boardY = pieceY + row;
                if (boardX < 0 || boardX >= WIDTH || boardY < 0 || boardY >= HEIGHT
                        || occupiedCells[boardY][boardX]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean[][] getVisibleCells(Piece piece) {
        boolean[][] cells = new boolean[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            System.arraycopy(occupiedCells[row], 0, cells[row], 0, WIDTH);
        }

        boolean[][] shape = piece.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                if (shape[row][column]) {
                    cells[piece.getY() + row][piece.getX() + column] = true;
                }
            }
        }
        return cells;
    }
}
