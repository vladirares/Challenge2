package com.playtika.java.training.challenge2.jelea.vladimir.config;

import com.playtika.java.training.challenge2.jelea.vladimir.exceptions.NoAvailablePlayersException;
import com.playtika.java.training.challenge2.jelea.vladimir.exceptions.NoGameSettingsException;
import com.playtika.java.training.challenge2.jelea.vladimir.models.BingoNumber;
import com.playtika.java.training.challenge2.jelea.vladimir.models.NumberType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSettings implements Cloneable {
    private static GameSettings instance;
    public static int noCards;
    private int noPlayers;
    private List<String> playerNames;
    private List<BingoNumber> forbiddenCombinations;

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings("settings.txt");
        }
        return instance;
    }

    public static GameSettings getInstance(String fileName) {
        if (instance == null) {
            instance = new GameSettings(fileName);
        }
        return instance;
    }

    private GameSettings(String settingsFileName) {
        playerNames = new ArrayList<>();
        forbiddenCombinations = new ArrayList<>();
        try {
            initialize(settingsFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNoPlayers() {
        return noPlayers;
    }

    public String getPlayerName(int index) {
        return playerNames.get(index);
    }

    public int getNoForbiddenCombinations() {
        return forbiddenCombinations.size();
    }

    public BingoNumber getForbiddenCombination(int index) {
        return forbiddenCombinations.get(index);
    }

    @Override
    protected GameSettings clone() throws CloneNotSupportedException {
        return instance; //singleton
    }

    private void initialize(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new NoGameSettingsException();
        }
        Scanner reader = new Scanner(file);
        noCards = reader.nextInt();
        noPlayers = reader.nextInt();
        if(noPlayers < 1){
            throw new NoAvailablePlayersException();
        }
        reader.nextLine();
        for (int i = 0; i < noPlayers; i++) {
            playerNames.add(reader.nextLine());
        }
        while (reader.hasNext()) {
            String[] input = reader.nextLine().split(",");
            for (int i = 1; i < 6; i++) {
                forbiddenCombinations.add(
                        new BingoNumber(NumberType.valueOf(input[0]), Integer.parseInt(input[i])));
            }
        }

    }

    @Override
    public String toString() {
        return "GameSettings{" +
                "noPlayers=" + noPlayers +
                ", playerNames=" + playerNames +
                ", forbiddenCombinations=" + forbiddenCombinations.toString() +
                '}';
    }
}
