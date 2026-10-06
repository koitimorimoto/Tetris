package com.example.tetris.state;

import com.example.tetris.manager.GameManager;

public class PausedState implements GameState {

    private final GameManager gameManager;

    public PausedState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void moveLeft() {
    }

    @Override
    public void moveRight() {
    }

    @Override
    public void moveDown() {
    }

    @Override
    public void rotate() {
    }

    @Override
    public void drop() {
    }

    @Override
    public void togglePause() {
        gameManager.resumeGame();
    }

    @Override
    public void restart() {
        gameManager.restartGame();
    }

    @Override
    public String getStatus() {
        return "PAUSADO — pressione P para continuar";
    }
}
