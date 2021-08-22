package com.playtika.java.training.challenge2.jelea.vladimir;

import com.playtika.java.training.challenge2.jelea.vladimir.config.GameSettings;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Generator;
import com.playtika.java.training.challenge2.jelea.vladimir.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        GameSettings gameSettings;

        if(args==null && args.length == 0){
            gameSettings = GameSettings.getInstance();
        }else{
            gameSettings = GameSettings.getInstance(args[0]);
        }
//
//        Generator generator = new BingoGenerator();
////        System.out.println(generator.generateCards(3));
//
//        List<PlayerCard> cards = new ArrayList<>();
//        List<Card> c = generator.generateCards(100);
//        for(Card ca : c){
//            cards.add(new PlayerCard(ca));
//        }
//
//        Caller caller = Caller.getInstance();
//        caller.start();
//
//        Player player = new Player(100,"vladimir");
//        player.setPlayerCards(cards);
//
//        ExecutorService service = Executors.newFixedThreadPool(4);
//        Future<Boolean> ans = service.submit(player);
//
//        System.out.println(ans.get());
//
//        service.shutdown();
//
//        if(!service.awaitTermination(800, TimeUnit.MILLISECONDS)){
//            service.shutdownNow();
//        }
//
//        caller.setGameFinished(true);
//
//        System.out.println("the end!");

        GameEngine gameEngine = new GameEngine();
        gameEngine.start();

    }
}
