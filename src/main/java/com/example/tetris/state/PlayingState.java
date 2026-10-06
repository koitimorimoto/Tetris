package com.example.tetris.state;

import com.example.tetris.manager.GameManager;

public class PlayingState implements GameState {

    private final GameManager gameManager;

    public PlayingState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void moveLeft() {
        gameManager.movePiece(-1, 0);
    }

    @Override
    public void moveRight() {
        gameManager.movePiece(1, 0);
    }

    @Override
    public void moveDown() {
        gameManager.movePiece(0, 1);
    }

    @Override
    public void rotate() {
        gameManager.rotatePiece();
    }

    @Override
    public void drop() {
        gameManager.dropPiece();
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
    public String getStatus() {
        return "Partida em andamento";
    }
}
