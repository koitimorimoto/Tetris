package com.example.tetris.command;

import com.example.tetris.facade.GameFacade;

public class MoveDownCommand implements Command {

    private final GameFacade gameFacade;

    public MoveDownCommand(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public boolean execute() {
        return gameFacade.moveDown();
    }
}
