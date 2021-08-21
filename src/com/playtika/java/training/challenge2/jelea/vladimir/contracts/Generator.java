package com.playtika.java.training.challenge2.jelea.vladimir.contracts;

import java.util.List;
import java.util.concurrent.Callable;

public interface Generator {
    List<Card> generateCards(int noCards);
}
