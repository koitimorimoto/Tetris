package com.example.tetris.state;

import com.example.tetris.manager.GameManager;

public class PlayingState implements GameState {

    private final GameManager gameManager;

    public PlayingState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean moveLeft() {
        return gameManager.movePiece(-1, 0);
    }

    @Override
    public boolean moveRight() {
        return gameManager.movePiece(1, 0);
    }

    @Override
    public boolean moveDown() {
        return gameManager.movePiece(0, 1);
    }

    @Override
    public boolean rotate() {
        return gameManager.rotatePiece();
    }

    @Override
    public boolean drop() {
        return gameManager.dropPiece();
    }

    @Override
    public void togglePause() {
        gameManager.pauseGame();
    }

    @Override
    public void restart() {
        gameManager.restartGame();
    }

    @Override
    public boolean update(long elapsedNanos) {
        return gameManager.updateGame(elapsedNanos);
    }

    @Override
    public String getStatus() {
        return "Partida em andamento";
    }
}
