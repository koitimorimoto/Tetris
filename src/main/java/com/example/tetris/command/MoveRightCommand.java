package com.example.tetris.command;

import com.example.tetris.facade.GameFacade;

public class MoveRightCommand implements Command {

    private final GameFacade gameFacade;

    public MoveRightCommand(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public boolean execute() {
        return gameFacade.moveRight();
    }
}
