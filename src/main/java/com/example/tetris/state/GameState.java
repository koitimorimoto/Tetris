package com.example.tetris.state;

public interface GameState {

    void moveLeft();

    void moveRight();

    void moveDown();

    void rotate();

    void drop();

    void togglePause();

    void restart();

    String getStatus();
}
