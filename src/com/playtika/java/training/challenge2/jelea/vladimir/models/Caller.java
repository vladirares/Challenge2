package com.playtika.java.training.challenge2.jelea.vladimir.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Caller extends Thread {
    private static Caller instance;
    volatile private char lastNumberColumn;
    volatile private int lastNumberNumber;
    private AtomicInteger noRounds;
    private List<BingoNumber> previousCombinations;
    private boolean isGameFinished;
    private Random random;

    public Caller() {
        random = new Random();
        lastNumberColumn = ' ';
        lastNumberNumber = 0;
        previousCombinations = new ArrayList<>();
        noRounds = new AtomicInteger();
    }

    public static Caller getInstance() {
        if(instance == null){
            instance = new Caller();
        }
        return instance;
    }

    private boolean wasPicked(BingoNumber bingoNumber) {
        return previousCombinations.contains(bingoNumber);
    }

    public char getLastColumn() {
        return lastNumberColumn;
    }

    public int getLastNumber() {
        return lastNumberNumber;
    }

    public int getNoOfPlayedNumbers() {
        return noRounds.get();
    }

    public List<BingoNumber> getPreviousCombinations() throws CloneNotSupportedException {
        List<BingoNumber> bingoNumbersCopy = new ArrayList<>();
        for (BingoNumber bingoNumber : previousCombinations) {
            bingoNumbersCopy.add(bingoNumber.clone());
        }
        return bingoNumbersCopy;
    }

    public boolean checkGameStatus() {
        return isGameFinished;
    }

    @Override
    public void run() {
        String[] columns = {"B", "I", "N", "G", "O"};

        while (true) {

            BingoNumber number;
            do {
                NumberType chosenColumn = NumberType.valueOf(columns[random.nextInt(5)]);
                int chosenNumber = random.nextInt(15) + chosenColumn.getLowValue();
                number = new BingoNumber(chosenColumn, chosenNumber);
            } while (wasPicked(number));

            previousCombinations.add(number);
            lastNumberColumn = number.getColumn().toString().charAt(0);
            lastNumberNumber = number.getNumber();
            noRounds.incrementAndGet();

            System.out.println(number);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
