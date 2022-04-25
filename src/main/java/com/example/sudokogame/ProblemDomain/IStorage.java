package com.example.sudokogame.ProblemDomain;

import java.io.IOException;

public interface IStorage {

    void updateGame(SudokuGame game) throws IOException;
    SudokuGame getGameData() throws IOException;
}
