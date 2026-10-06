package com.example.tetris.model;

public enum PieceType {
    I("....", "####", "....", "...."),
    O(".##.", ".##.", "....", "...."),
    T(".###", "..#.", "....", "...."),
    L("...#", ".###", "....", "...."),
    J(".#..", ".###", "....", "...."),
    S("..##", ".##.", "....", "...."),
    Z(".##.", "..##", "....", "....");

    private final String[] rows;

    PieceType(String... rows) {
        this.rows = rows;
    }

    public boolean[][] createShape() {
        boolean[][] shape = new boolean[rows.length][];
        for (int row = 0; row < rows.length; row++) {
            shape[row] = new boolean[rows[row].length()];
            for (int column = 0; column < rows[row].length(); column++) {
                shape[row][column] = rows[row].charAt(column) == '#';
            }
        }
        return shape;
    }
}
