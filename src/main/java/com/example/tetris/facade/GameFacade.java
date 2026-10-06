package com.example.tetris.facade;

import com.example.tetris.manager.GameManager;

public class GameFacade {

    private final GameManager gameManager;

    public GameFacade(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean moveLeft() {
        return gameManager.moveLeft();
    }

    public boolean moveRight() {
        return gameManager.moveRight();
    }

    public boolean moveDown() {
        return gameManager.moveDown();
    }

    public boolean rotate() {
        return gameManager.rotate();
    }

    public boolean drop() {
        return gameManager.drop();
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

    public boolean[][] getNextPieceShape() {
        return gameManager.getNextPieceShape();
    }

    public boolean[][] getLandingCells() {
        return gameManager.getLandingCells();
    }

    public String getStatus() {
        return gameManager.getStatus();
    }

    public int getScore() {
        return gameManager.getScore();
    }

    public int getLevel() {
        return gameManager.getLevel();
    }

    public int getTotalLinesCleared() {
        return gameManager.getTotalLinesCleared();
    }

    public boolean update(long elapsedNanos) {
        return gameManager.update(elapsedNanos);
    }
}
