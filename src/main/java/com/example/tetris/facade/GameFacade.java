package com.example.tetris.facade;

import com.example.tetris.manager.GameManager;

public class GameFacade {

    private final GameManager gameManager;

    public GameFacade(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void moveLeft() {
        gameManager.moveLeft();
    }

    public void moveRight() {
        gameManager.moveRight();
    }

    public void moveDown() {
        gameManager.moveDown();
    }

    public void rotate() {
        gameManager.rotate();
    }

    public void drop() {
        gameManager.drop();
    }

    public void togglePause() {
        gameManager.togglePause();
    }

    public void restart() {
        gameManager.restart();
    }

    public boolean[][] getVisibleCells() {
        return gameManager.getVisibleCells();
    }

    public String getStatus() {
        return gameManager.getStatus();
    }
}
