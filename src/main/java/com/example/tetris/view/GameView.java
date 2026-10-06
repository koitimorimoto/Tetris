package com.example.tetris.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView {

    private static final int CELL_SIZE = 30;
    private static final int COLUMNS = 10;
    private static final int ROWS = 20;

    private final Canvas canvas = new Canvas(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
    private final Label statusLabel = new Label();
    private final VBox root;

    public GameView() {
        Label controls = new Label("← → mover   ↓ descer   ↑ girar   Espaço soltar   P pausar   R reiniciar");
        controls.setTextFill(Color.LIGHTGRAY);
        statusLabel.setTextFill(Color.WHITE);

        root = new VBox(12, statusLabel, canvas, controls);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #171923;");
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public VBox getRoot() {
        return root;
    }

    public void render(boolean[][] cells, String status) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.rgb(25, 29, 43));
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                double x = column * CELL_SIZE;
                double y = row * CELL_SIZE;
                if (cells[row][column]) {
                    graphics.setFill(Color.rgb(76, 201, 240));
                    graphics.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
                }
                graphics.setStroke(Color.rgb(55, 61, 78));
                graphics.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
        statusLabel.setText(status);
    }
}