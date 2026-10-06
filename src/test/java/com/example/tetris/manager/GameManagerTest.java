package com.example.tetris.manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameManagerTest {

    @Test
    void pausedStateIgnoresMovementAndCanResume() {
        GameManager manager = GameManager.getInstance();
        manager.restart();
        boolean[][] beforePause = manager.getVisibleCells();

        manager.togglePause();
        manager.moveLeft();

        assertEquals("PAUSADO — pressione P para continuar", manager.getStatus());
        assertBoardsEqual(beforePause, manager.getVisibleCells());

        manager.togglePause();
        assertEquals("Partida em andamento", manager.getStatus());
        manager.restart();
    }

    private void assertBoardsEqual(boolean[][] expected, boolean[][] actual) {
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row]);
        }
    }
}
