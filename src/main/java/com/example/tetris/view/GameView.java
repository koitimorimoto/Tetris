package com.example.tetris.view;

import javafx.scene.canvas.Canvas;

public class GameView {

    private final Canvas canvas;

    public GameView() {
        canvas = new Canvas(300, 600);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}