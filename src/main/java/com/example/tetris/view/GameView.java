package com.example.tetris.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView {

    private static final int CELL_SIZE = 30;
    private static final int COLUMNS = 10;
    private static final int ROWS = 20;
    private static final Color WINDOW_COLOR = Color.rgb(188, 188, 188);
    private static final Color BOARD_COLOR = Color.rgb(205, 205, 205);
    private static final Color PIECE_COLOR = Color.rgb(65, 65, 65);
    private static final Color GRID_COLOR = Color.rgb(145, 145, 145);
    private static final Color LANDING_PREVIEW_COLOR = Color.rgb(119, 132, 101, 0.72);

    private final Canvas canvas = new Canvas(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
    private final Canvas nextPieceCanvas = new Canvas(4 * CELL_SIZE, 4 * CELL_SIZE);
    private final Label statusLabel = new Label();
    private final Label scoreValueLabel = new Label();
    private final Label levelValueLabel = new Label();
    private final Label linesValueLabel = new Label();
    private final HBox root;

    public GameView() {
        Label nextPieceLabel = new Label("Próxima peça");
        VBox preview = new VBox(8, nextPieceLabel, nextPieceCanvas);
        preview.setAlignment(Pos.TOP_CENTER);
        stylePanel(preview);

        Label scoreLabel = new Label("Score");
        Label levelLabel = new Label("Level");
        Label linesLabel = new Label("Linhas");
        VBox statistics = new VBox(12,
                scoreLabel, scoreValueLabel,
                levelLabel, levelValueLabel,
                linesLabel, linesValueLabel);
        statistics.setAlignment(Pos.TOP_LEFT);
        stylePanel(statistics);

        Label controls = new Label("← → mover   ↓ descer   ↑ girar   Espaço soltar   P pausar   R reiniciar");
        VBox boardColumn = new VBox(10, canvas, statusLabel, controls);
        boardColumn.setAlignment(Pos.CENTER);
        stylePanel(boardColumn);

        root = new HBox(24, preview, boardColumn, statistics);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #bcbcbc; -fx-font-family: 'Monospaced'; -fx-font-size: 13px;");
    }

    public HBox getRoot() {
        return root;
    }

    public void render(boolean[][] cells, boolean[][] landingCells, boolean[][] nextPiece, String status,
                       int score, int level, int lines) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(BOARD_COLOR);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                double x = column * CELL_SIZE;
                double y = row * CELL_SIZE;
                if (landingCells[row][column] && !cells[row][column]) {
                    graphics.setFill(LANDING_PREVIEW_COLOR);
                    graphics.fillRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                }
                if (cells[row][column]) {
                    graphics.setFill(PIECE_COLOR);
                    graphics.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
                }
                graphics.setStroke(GRID_COLOR);
                graphics.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }

        drawNextPiece(nextPiece);
        statusLabel.setText(status);
        scoreValueLabel.setText(Integer.toString(score));
        levelValueLabel.setText(Integer.toString(level));
        linesValueLabel.setText(Integer.toString(lines));
    }

    private void drawNextPiece(boolean[][] shape) {
        GraphicsContext graphics = nextPieceCanvas.getGraphicsContext2D();
        graphics.setFill(BOARD_COLOR);
        graphics.fillRect(0, 0, nextPieceCanvas.getWidth(), nextPieceCanvas.getHeight());
        graphics.setStroke(GRID_COLOR);
        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                double x = column * CELL_SIZE;
                double y = row * CELL_SIZE;
                if (shape[row][column]) {
                    graphics.setFill(PIECE_COLOR);
                    graphics.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
                }
                graphics.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void stylePanel(VBox panel) {
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: #c8c8c8; -fx-border-color: #777777;");
    }
}