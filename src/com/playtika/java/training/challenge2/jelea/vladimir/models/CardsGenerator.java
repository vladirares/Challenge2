package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CardsGenerator {
    private int noThreads;
    private int noCards;
    private boolean hasFinishedGeneratingData;
    private Generator generator;

    public CardsGenerator(int noThreads, int noCards, Generator generator) {
        this.noThreads = noThreads;
        this.noCards = noCards;
        this.generator = generator;
    }

    public List<PlayerCard> getInitialCards() throws ExecutionException, InterruptedException {
        int chunkSize = noCards / noThreads;
        ExecutorService service = Executors.newFixedThreadPool(noThreads);
        Callable<List<Card>> task = () -> generator.generateCards(chunkSize);
        List<Future<List<Card>>> cardsFuture = new ArrayList<>();
        List<List<Card>> cards = new ArrayList<>();

        for (int i = 0; i < noThreads; i++) {
            if (i == noThreads - 1) {
                cardsFuture.add(service.submit(() -> generator.generateCards(chunkSize + (noCards % (chunkSize * noThreads)))));
            } else {
                cardsFuture.add(service.submit(() -> generator.generateCards(chunkSize)));
            }
        }

        for (Future<List<Card>> card : cardsFuture) {
            cards.add(card.get());
        }

        service.shutdown();
        if(!service.awaitTermination(800,TimeUnit.MILLISECONDS)){
            service.shutdownNow();
        }

        List<PlayerCard> result = new ArrayList<>();

        for (List<Card> cardList : cards){
            for (Card card : cardList){
                result.add(new PlayerCard(card));
            }
        }

        return result;
    }

    public boolean hasFinished() {
        return hasFinishedGeneratingData;
    }

}
