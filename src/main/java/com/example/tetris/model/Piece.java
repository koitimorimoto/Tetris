package com.example.tetris.model;

public class Piece {

    private final PieceType type;
    private boolean[][] shape;
    private int x;
    private int y;

    public Piece(PieceType type, int x, int y) {
        this.type = type;
        this.shape = type.createShape();
        this.x = x;
        this.y = y;
    }

    public PieceType getType() {
        return type;
    }

    public boolean[][] getShape() {
        boolean[][] copy = new boolean[shape.length][];
        for (int row = 0; row < shape.length; row++) {
            copy[row] = shape[row].clone();
        }
        return copy;
    }

    public boolean[][] getRotatedShape() {
        int size = shape.length;
        boolean[][] rotated = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                rotated[column][size - row - 1] = shape[row][column];
            }
        }
        return rotated;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
