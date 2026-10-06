package com.example.tetris.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameModelTest {

    @Test
    void startsWithTheExpectedBoardAndPiece() {
        boolean[][] cells = new GameModel().getVisibleCells();

        assertEquals(Board.HEIGHT, cells.length);
        assertEquals(Board.WIDTH, cells[0].length);
        assertEquals(4, countCells(cells));
    }

    @Test
    void movementStopsAtTheLeftBoundary() {
        GameModel model = new GameModel();

        for (int i = 0; i < Board.WIDTH; i++) {
            model.movePiece(-1, 0);
        }

        assertEquals(0, leftmostColumn(model.getVisibleCells()));
        assertEquals(4, countCells(model.getVisibleCells()));
    }

    @Test
    void hardDropMovesThePieceToTheBottom() {
        GameModel model = new GameModel();

        model.dropPiece();

        assertEquals(4, countCells(model.getVisibleCells()));
        assertEquals(Board.HEIGHT - 1, lowestRow(model.getVisibleCells()));
    }

    private int countCells(boolean[][] cells) {
        int count = 0;
        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
        }
        return count;
    }

    private int leftmostColumn(boolean[][] cells) {
        for (int column = 0; column < Board.WIDTH; column++) {
            for (boolean[] row : cells) {
                if (row[column]) {
                    return column;
                }
            }
        }
        return -1;
    }

    private int lowestRow(boolean[][] cells) {
        for (int row = Board.HEIGHT - 1; row >= 0; row--) {
            for (boolean cell : cells[row]) {
                if (cell) {
                    return row;
                }
            }
        }
        return -1;
    }
}
