package com.example.tetris.command;

import com.example.tetris.facade.GameFacade;

public class DropCommand implements Command {

    private final GameFacade gameFacade;

    public DropCommand(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public boolean execute() {
        return gameFacade.drop();
    }
}
