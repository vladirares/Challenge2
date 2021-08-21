package com.playtika.java.training.challenge2.jelea.vladimir;

import com.playtika.java.training.challenge2.jelea.vladimir.config.GameSettings;

public class Main {

    public static void main(String[] args) {
        GameSettings gameSettings;

        if(args==null && args.length == 0){
            gameSettings = GameSettings.getInstance();
        }else{
            gameSettings = GameSettings.getInstance(args[0]);
        }

        System.out.println(gameSettings);

    }
}
