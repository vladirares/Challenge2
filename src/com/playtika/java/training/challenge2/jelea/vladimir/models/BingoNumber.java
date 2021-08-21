package com.playtika.java.training.challenge2.jelea.vladimir.models;

import com.playtika.java.training.challenge2.jelea.vladimir.contracts.Printable;

import java.util.Objects;

public class BingoNumber implements Cloneable, Printable {
    private NumberType column;
    private int number;

    public BingoNumber(NumberType column, int number) {
        this.column = column;
        this.number = number;
    }

    public static boolean isValidCombination(NumberType column, int number) {
        return column.isInRange(number);
    }

    public NumberType getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public BingoNumber clone() throws CloneNotSupportedException {
        return new BingoNumber(getColumn(), getNumber());
    }

    @Override
    public void print() {
        System.out.println(column + "-" + number);
    }

    @Override
    public String toString() {
        return column + "-" + number;
    }

    @Override
    public boolean equals(Object o) {
        return (((BingoNumber)o).getColumn().equals(this.getColumn()))
                && (((BingoNumber)o).getNumber() == this.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, number);
    }
}
