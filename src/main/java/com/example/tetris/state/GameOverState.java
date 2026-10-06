package com.example.tetris.state;

import com.example.tetris.manager.GameManager;

public class GameOverState implements GameState {

    private final GameManager gameManager;

    public GameOverState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public boolean moveDown() {
        return false;
    }

    @Override
    public boolean rotate() {
        return false;
    }

    @Override
    public boolean drop() {
        return false;
    }

    @Override
    public void togglePause() {
    }

    @Override
    public void restart() {
        gameManager.gameOverRestart();
    }

    @Override
    public boolean update(long elapsedNanos) {
        return false;
    }

    @Override
    public String getStatus() {
        return "GAME OVER — pressione R para reiniciar";
    }
}
