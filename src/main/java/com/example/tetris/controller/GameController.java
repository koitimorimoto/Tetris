package com.example.tetris.controller;

import com.example.tetris.command.Command;
import com.example.tetris.command.DropCommand;
import com.example.tetris.command.MoveDownCommand;
import com.example.tetris.command.MoveLeftCommand;
import com.example.tetris.command.MoveRightCommand;
import com.example.tetris.command.RotateCommand;
import com.example.tetris.facade.GameFacade;
import com.example.tetris.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

public class GameController {

    private final GameFacade gameFacade;
    private final GameView gameView;
    private final Map<KeyCode, Command> commands;
    private long previousFrameNanos;

    public GameController(GameFacade gameFacade, GameView gameView) {
        this.gameFacade = gameFacade;
        this.gameView = gameView;
        commands = Map.of(
                KeyCode.LEFT, new MoveLeftCommand(gameFacade),
                KeyCode.RIGHT, new MoveRightCommand(gameFacade),
                KeyCode.DOWN, new MoveDownCommand(gameFacade),
                KeyCode.UP, new RotateCommand(gameFacade),
                KeyCode.SPACE, new DropCommand(gameFacade)
        );
    }

    public void connect(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (previousFrameNanos != 0) {
                    if (gameFacade.update(now - previousFrameNanos)) {
                        refreshView();
                    }
                }
                previousFrameNanos = now;
            }
        }.start();
    }

    public void refreshView() {
        gameView.render(
                gameFacade.getVisibleCells(),
                gameFacade.getLandingCells(),
                gameFacade.getNextPieceShape(),
                gameFacade.getStatus(),
                gameFacade.getScore(),
                gameFacade.getLevel(),
                gameFacade.getTotalLinesCleared()
        );
    }

    private void handleKeyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == KeyCode.P) {
            gameFacade.togglePause();
        } else if (key == KeyCode.R) {
            gameFacade.restart();
        } else {
            Command command = commands.get(key);
            if (command == null) {
                return;
            }
            command.execute();
        }
        refreshView();
        event.consume();
    }
}
