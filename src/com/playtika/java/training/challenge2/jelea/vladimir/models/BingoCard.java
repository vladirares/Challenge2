package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;

import java.util.List;
import java.util.Map;

public class BingoCard implements Card {
    private Map<NumberType, List<Integer>> numbers;

    public BingoCard(Map<NumberType, List<Integer>> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        StringBuilder cardString = new StringBuilder();
        for(NumberType numberType : NumberType.values()){
            cardString.append(numberType.toString())
                    .append(":")
                    .append(cardNumbers(numberType))
                    .append("\n");
        }
        cardString.append("\n");
        return cardString.toString();
    }

    private String cardNumbers(NumberType numberType){
        List<Integer> numberList = numbers.get(numberType);
        StringBuilder result = new StringBuilder();
        for(Integer number : numberList){
            result.append(number)
                    .append("|");
        }
        return result.toString();
    }

}


