package com.playtika.java.training.challenge2.jelea.vladimir;

import com.playtika.java.training.challenge2.jelea.vladimir.config.GameSettings;
import com.playtika.java.training.challenge2.jelea.vladimir.models.BingoCard;
import com.playtika.java.training.challenge2.jelea.vladimir.models.BingoGenerator;
import com.playtika.java.training.challenge2.jelea.vladimir.models.CardsGenerator;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        GameSettings gameSettings;

        if(args==null && args.length == 0){
            gameSettings = GameSettings.getInstance();
        }else{
            gameSettings = GameSettings.getInstance(args[0]);
        }

        System.out.println(gameSettings);


        CardsGenerator generator = new CardsGenerator(4,4,new BingoGenerator());

        System.out.println(generator.getInitialCards());

    }
}
