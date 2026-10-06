package com.example.tetris.model;

public class Board {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;

    private final boolean[][] occupiedCells = new boolean[HEIGHT][WIDTH];

    public Board() {
    }

    Board(boolean[][] initialCells) {
        if (initialCells.length != HEIGHT) {
            throw new IllegalArgumentException("Board must have exactly " + HEIGHT + " rows.");
        }
        for (int row = 0; row < HEIGHT; row++) {
            if (initialCells[row].length != WIDTH) {
                throw new IllegalArgumentException("Board rows must have exactly " + WIDTH + " columns.");
            }
            System.arraycopy(initialCells[row], 0, occupiedCells[row], 0, WIDTH);
        }
    }

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

    public void lockPiece(Piece piece) {
        boolean[][] shape = piece.getShape();
        if (!canPlace(shape, piece.getX(), piece.getY())) {
            throw new IllegalStateException("Cannot lock a piece in an occupied position.");
        }

        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                if (shape[row][column]) {
                    occupiedCells[piece.getY() + row][piece.getX() + column] = true;
                }
            }
        }
    }

    public int clearFullLines() {
        int writeRow = HEIGHT - 1;
        int clearedLines = 0;

        for (int readRow = HEIGHT - 1; readRow >= 0; readRow--) {
            if (isFullRow(readRow)) {
                clearedLines++;
                continue;
            }

            if (writeRow != readRow) {
                System.arraycopy(occupiedCells[readRow], 0, occupiedCells[writeRow], 0, WIDTH);
            }
            writeRow--;
        }

        while (writeRow >= 0) {
            occupiedCells[writeRow--] = new boolean[WIDTH];
        }

        return clearedLines;
    }

    private boolean isFullRow(int row) {
        for (boolean occupied : occupiedCells[row]) {
            if (!occupied) {
                return false;
            }
        }
        return true;
    }

    public boolean[][] getVisibleCells(Piece piece) {
        boolean[][] cells = new boolean[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            System.arraycopy(occupiedCells[row], 0, cells[row], 0, WIDTH);
        }

        if (piece == null) {
            return cells;
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
