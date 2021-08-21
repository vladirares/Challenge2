package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Player implements Callable<Boolean> {
    private int noCards;
    private String name;
    private List<PlayerCard> ownedCards;
    private boolean isBingoTime;
    private String winningSchema;
    private List<BingoNumber> previousNumbers;

    public Player(int noCards, String name) {
        this.noCards = noCards;
        this.name = name;
        previousNumbers = new ArrayList<>();
        isBingoTime = true;
    }

    public void setPlayerCards(List<PlayerCard> ownedCards) {
        this.ownedCards = new ArrayList<>(ownedCards);
    }

    public int getNoCards() {
        return noCards;
    }

    public void checkCardsToWin() {
        while(isBingoTime) {
            for (PlayerCard card : ownedCards) {
                Card bingoCard = card.getCard();
                if (isFourCorners(bingoCard) || isSmallDiamond(bingoCard)
                        || isPostageStamp(bingoCard) || isVerticalLine(bingoCard)
                        || isHorizontalLine(bingoCard) || isDiagonalLine(bingoCard)) {
                    winningSchema = card.toString();
                    System.out.println("wwwwwwwwwwwww\n"+winningSchema);
                    isBingoTime = false;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasSaidBingo() {

        return true;
    }

    @Override
    public Boolean call() throws Exception {
        new Thread(this::checkCardsToWin).start();

        while(isBingoTime) {
            BingoNumber lastNumber = new BingoNumber(NumberType.valueOf(Caller.getInstance().getLastColumn() + "")
                    , Caller.getInstance().getLastNumber());
            if (previousNumbers.size() < Caller.getInstance().getNoOfPlayedNumbers()) {
                previousNumbers.add(lastNumber);
            }
            Thread.sleep(10);
        }

        return true;//todo
    }

    private boolean isSmallDiamond(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.I, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 1, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.G, 2, card)) {
            return false;
        }

        return true;
    }

    private boolean isPostageStamp(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 1, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.I, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.I, 1, card)) {
            return false;
        }
        return true;
    }

    private boolean isFourCorners(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 4, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.O, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.O, 4, card)) {
            return false;
        }
        return true;
    }

    private boolean isDiagonalLine(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.I, 1, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.G, 3, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.O, 4, card)) {
            return false;
        }
        return true;
    }

    private boolean isHorizontalLine(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.B, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.I, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.G, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.O, 2, card)) {
            return false;
        }
        return true;
    }

    private boolean isVerticalLine(Card card) {
        if (!(card instanceof BingoCard)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 0, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 1, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 2, card)) {
            return false;
        }
        if (!containsAtPosition(NumberType.N, 3, card)) {
            return false;
        }
        return true;
    }

    private boolean containsAtPosition(NumberType numberType, int index, Card card) {
        return previousNumbers.contains(
                new BingoNumber(numberType, ((BingoCard) card).getNumbers().get(numberType).get(index)));
    }

}
