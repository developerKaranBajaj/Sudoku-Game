package com.example.sudokogame.userinterface.logic;

import com.example.sudokogame.ProblemDomain.IStorage;
import com.example.sudokogame.ProblemDomain.SudokuGame;
import com.example.sudokogame.computationlogic.GameLogic;
import com.example.sudokogame.constants.GameState;
import com.example.sudokogame.constants.Messages;
import com.example.sudokogame.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {

        try{
            SudokuGame gameData = storage.getGameData();
            int[][] newGridStage = gameData.getCopyOfGridState();
            newGridStage[x][y] = input;

            gameData = new SudokuGame(
                    GameLogic.checkForCompletion(newGridStage),newGridStage
            );
            storage.updateGame(gameData);

            view.updateSquare(x, y, input);

            if(gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        }catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);

        }

    }

    @Override
    public void onDialogClick() {

        try {
            storage.updateGame(
                    GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());
        }catch (IOException e){
            view.showError(Messages.ERROR);
        }
    }
}
