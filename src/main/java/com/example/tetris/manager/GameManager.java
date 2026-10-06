package com.example.tetris.manager;

import com.example.tetris.model.GameModel;
import com.example.tetris.state.GameOverState;
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

    public boolean moveLeft() {
        return state.moveLeft();
    }

    public boolean moveRight() {
        return state.moveRight();
    }

    public boolean moveDown() {
        return state.moveDown();
    }

    public boolean rotate() {
        return state.rotate();
    }

    public boolean drop() {
        return state.drop();
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

    public boolean[][] getNextPieceShape() {
        return model.getNextPieceShape();
    }

    public boolean[][] getLandingCells() {
        return model.getLandingCells();
    }

    public String getStatus() {
        return state.getStatus();
    }

    public int getScore() {
        return model.getScore();
    }

    public int getLevel() {
        return model.getLevel();
    }

    public int getTotalLinesCleared() {
        return model.getTotalLinesCleared();
    }

    public long getFallIntervalNanos() {
        return model.getFallIntervalNanos();
    }

    public boolean movePiece(int dx, int dy) {
        return model.movePiece(dx, dy);
    }

    public boolean rotatePiece() {
        return model.rotatePiece();
    }

    public boolean dropPiece() {
        boolean dropped = model.dropPiece();
        if (model.isGameOver()) {
            state = new GameOverState(this);
        }
        return dropped;
    }

    public boolean updateGame(long elapsedNanos) {
        boolean changed = model.update(elapsedNanos);
        if (model.isGameOver()) {
            state = new GameOverState(this);
        }
        return changed;
    }

    public boolean update(long elapsedNanos) {
        return state.update(elapsedNanos);
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

    public void gameOverRestart() {
        restartGame();
    }
}
