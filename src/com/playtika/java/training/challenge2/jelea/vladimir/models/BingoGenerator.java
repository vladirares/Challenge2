package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Generator;

import java.util.*;
import java.util.concurrent.Callable;

public class BingoGenerator implements Generator {
    private Random random = new Random();

    @Override
    public List<Card> generateCards(int noCards){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i < noCards; i++){
            cards.add(generateCard());
        }
        return cards;
    }

    private Card generateCard(){
        Map<NumberType,List<Integer>> card = new HashMap<>();
        for(NumberType numberType : NumberType.values()){
            Set<Integer> generatedNumbers= new HashSet<>();
            int noOfGeneratedNumbers = numberType.equals(NumberType.N) ? 4 : 5;
            while(generatedNumbers.size() < noOfGeneratedNumbers){
                generatedNumbers.add(random.nextInt(15)+numberType.getLowValue());
            }
            card.put(numberType,new ArrayList<>(generatedNumbers));
        }
        return new BingoCard(card);
    }

}
