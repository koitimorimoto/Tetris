package com.example.tetris.state;

public interface GameState {

    boolean moveLeft();

    boolean moveRight();

    boolean moveDown();

    boolean rotate();

    boolean drop();

    void togglePause();

    void restart();

    boolean update(long elapsedNanos);

    String getStatus();
}
