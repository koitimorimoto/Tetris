package com.example.tetris.command;

import com.example.tetris.facade.GameFacade;

public class MoveLeftCommand implements Command {

    private final GameFacade gameFacade;

    public MoveLeftCommand(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public boolean execute() {
        return gameFacade.moveLeft();
    }
}
