package com.example.sudokogame.buildlogic;

import com.example.sudokogame.ProblemDomain.IStorage;
import com.example.sudokogame.ProblemDomain.SudokuGame;
import com.example.sudokogame.computationlogic.GameLogic;
import com.example.sudokogame.persistence.LocalStorageImpl;
import com.example.sudokogame.userinterface.IUserInterfaceContract;
import com.example.sudokogame.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        }catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGame(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
