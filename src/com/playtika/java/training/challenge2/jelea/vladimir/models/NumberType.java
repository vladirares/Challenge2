package com.playtika.java.training.challenge2.jelea.vladimir.models;

public enum NumberType {
    B(1, 15),
    I(16, 30),
    N(31, 45),
    G(46, 60),
    O(61, 75);

    private int lowValue;
    private int highValue;

    NumberType(int lowValue, int highValue) {
        this.lowValue = lowValue;
        this.highValue = highValue;
    }

    public boolean isInRange(int number) {
        return (number >= lowValue) && (number <= highValue);
    }

    public int getLowValue() {
        return lowValue;
    }

    public int getHighValue() {
        return highValue;
    }
}
