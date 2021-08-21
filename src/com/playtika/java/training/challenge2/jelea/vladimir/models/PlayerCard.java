package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;
import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Printable;

import java.util.UUID;

public class PlayerCard implements Printable {
    public final UUID UID;
    private Card card;

    public PlayerCard(Card card) {
        UID = UUID.randomUUID();
        this.card = card;
    }

    @Override
    public void print() {
        System.out.println("Card UID: " + UID);
        System.out.println(card);
    }

    @Override
    public String toString() {
        return "PlayerCard{" +
                "UID=" + UID +
                ", card=" + card +
                '}';
    }
}
