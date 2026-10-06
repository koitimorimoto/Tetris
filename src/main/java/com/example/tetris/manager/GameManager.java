package com.example.tetris.manager;

import com.example.tetris.model.GameModel;
import com.example.tetris.state.GameState;
import com.example.tetris.state.PausedState;
import com.example.tetris.state.PlayingState;

public final class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private final GameModel model = new GameModel();
    private GameState state;

    private GameManager() {
        state = new PlayingState(this);
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public void startGame() {
        restartGame();
    }

    public void moveLeft() {
        state.moveLeft();
    }

    public void moveRight() {
        state.moveRight();
    }

    public void moveDown() {
        state.moveDown();
    }

    public void rotate() {
        state.rotate();
    }

    public void drop() {
        state.drop();
    }

    public void togglePause() {
        state.togglePause();
    }

    public void restart() {
        state.restart();
    }

    public boolean[][] getVisibleCells() {
        return model.getVisibleCells();
    }

    public String getStatus() {
        return state.getStatus();
    }

    public void movePiece(int dx, int dy) {
        model.movePiece(dx, dy);
    }

    public void rotatePiece() {
        model.rotatePiece();
    }

    public void dropPiece() {
        model.dropPiece();
    }

    public void restartGame() {
        model.reset();
        state = new PlayingState(this);
    }

    public void pauseGame() {
        state = new PausedState(this);
    }

    public void resumeGame() {
        state = new PlayingState(this);
    }
}
