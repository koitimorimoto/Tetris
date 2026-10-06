package com.example.tetris;

import com.example.tetris.controller.GameController;
import com.example.tetris.facade.GameFacade;
import com.example.tetris.manager.GameManager;
import com.example.tetris.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TetrisApplication extends Application {

    @Override
    public void start(Stage stage) {
        GameManager manager = GameManager.getInstance();
        manager.startGame();
        GameFacade facade = new GameFacade(manager);
        GameView gameView = new GameView();
        GameController controller = new GameController(facade, gameView);
        StackPane root = new StackPane(gameView.getRoot());
        root.setFocusTraversable(true);
        Scene scene = new Scene(root);
        controller.connect(scene);
        controller.refreshView();

        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }
}