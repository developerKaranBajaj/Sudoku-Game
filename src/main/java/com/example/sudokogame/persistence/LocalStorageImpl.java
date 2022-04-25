package com.example.sudokogame.persistence;

import com.example.sudokogame.ProblemDomain.IStorage;
import com.example.sudokogame.ProblemDomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(System.getProperty("user.home"), "gameData.txt");

    @Override
    public void updateGame(SudokuGame game) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        }catch (IOException e){
            throw new IOException("Unable to access GameData");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {

        FileInputStream fileInputStream =  new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            return gameState;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new IOException("File not found");
        }

    }
}
