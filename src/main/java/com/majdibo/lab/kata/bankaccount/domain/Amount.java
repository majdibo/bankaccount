package com.majdibo.lab.kata.bankaccount.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Amount representation, values are in euro and accept decimals
 */
public class Amount implements Comparable<Amount> {

    public static final Amount ZERO_AMOUNT = Amount.of(0.0);

    private final BigDecimal amountValue;

    private Amount(BigDecimal amountValue) {
        this.amountValue = amountValue;
    }


    public static Amount of(Double amountValue) {
        if (amountValue == null) {
            return ZERO_AMOUNT;
        }

        return of(BigDecimal.valueOf(amountValue));
    }

    public static Amount of(BigDecimal amountValue) {
        if (amountValue == null) {
            return ZERO_AMOUNT;
        }

        return new Amount(amountValue);
    }

    @Override
    public String toString() {
        return amountValue.toString();
    }

    public Amount subtract(Amount amount) {
        return Amount.of(amountValue.subtract(ofNullable(amount).orElse(ZERO_AMOUNT).amountValue));
    }

    public Amount add(Amount amount) {
        return Amount.of(amountValue.add(ofNullable(amount).orElse(ZERO_AMOUNT).amountValue));
    }


    @Override
    public int compareTo(Amount amount) {
        return amountValue.compareTo(ofNullable(amount).orElse(ZERO_AMOUNT).amountValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(amountValue, amount.amountValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountValue);
    }
}
