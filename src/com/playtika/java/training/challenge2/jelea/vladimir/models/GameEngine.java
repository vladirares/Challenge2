package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.config.GameSettings;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Generator;
import com.playtika.java.training.challenge2.jelea.vladimir.models.card.PlayerCard;
import com.playtika.java.training.challenge2.jelea.vladimir.models.generator.BingoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class GameEngine {
    private GameSettings gameSettings;
    private List<Player> players;
    private Caller caller;
    private Random random;
    private Generator generator;
    private List<Future<Boolean>> playerStatuses;
    private ExecutorService service;

    public GameEngine() {
        gameSettings = GameSettings.getInstance();
        random = new Random();
        players = new ArrayList<>();
        generator = new BingoGenerator();
        playerStatuses = new ArrayList<>();

    }

    public void start(){
        initPlayers();
        giveCards();
        startGame();
        while(!caller.isGameFinished()){
            checkIfWinner();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initPlayers() {
        int noCards;
        for (int i = 0; i < gameSettings.getNoPlayers(); i++) {
            noCards = random.nextInt(20) + 1;
            players.add(new Player(noCards, gameSettings.getPlayerName(i)));
        }
    }

    private void giveCards() {
        List<PlayerCard> cards = new ArrayList<>();
        List<Card> c = generator.generateCards(GameSettings.noCards);
        for (Card ca : c) {
            cards.add(new PlayerCard(ca));
        }
        for (Player player : players) {
            int noCards = player.getNoCards();
            List<PlayerCard> playerCards = new ArrayList<>(cards.subList(0, noCards + 1));
            cards = cards.subList(noCards + 1, cards.size());
            player.setPlayerCards(playerCards);
        }
    }

    private void startGame() {
        caller = Caller.getInstance();
        caller.start();
        service = Executors.newFixedThreadPool(4);
        for (Player player : players) {
            playerStatuses.add(service.submit(player));
        }
    }

    private void checkIfWinner(){
        for (Future<Boolean> status : playerStatuses) {
            try {
                if ((status.isDone() || status.isCancelled()) && status.get()) {
                    stopPlayers();
                    caller.setGameFinished(true);
                    service.shutdownNow();
                    if(!service.awaitTermination(800, TimeUnit.MILLISECONDS)){
                        service.shutdownNow();
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopPlayers(){
        for(Player player : players){
            player.setStop(true);
        }
    }


}
