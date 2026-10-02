package com.example.tetris;

import com.example.tetris.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TetrisApplication extends Application {

    @Override
    public void start(Stage stage) {

        GameView gameView = new GameView();

        Pane root = new Pane();
        root.getChildren().add(gameView.getCanvas());

        Scene scene = new Scene(root, 600, 800);

        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }
}