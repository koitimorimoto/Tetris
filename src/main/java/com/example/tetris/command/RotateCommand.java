package com.example.tetris.command;

import com.example.tetris.facade.GameFacade;

public class RotateCommand implements Command {

    private final GameFacade gameFacade;

    public RotateCommand(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void execute() {
        gameFacade.rotate();
    }
}
