package com.majdibo.lab.kata.bankaccount.domain;


import com.majdibo.lab.kata.bankaccount.core.test.Test;
import com.majdibo.lab.kata.bankaccount.core.test.TestFailError;
import com.majdibo.lab.kata.bankaccount.core.test.Tests;

public class AmountTest extends Tests {
    public AmountTest() {
    }


    @Test
    public void pass_null_as_a_parameter_to_create_amount() throws TestFailError {
        Double nullValue = null;
        var amount = Amount.of(nullValue);
        assertEquals(amount, Amount.ZERO_AMOUNT);
    }

    @Test
    public void substract_amount() throws TestFailError {
        var amount1 = Amount.of(100.0);
        var amount2 = Amount.of(34.5);
        Amount substract = amount1.subtract(amount2);

        assertEquals(substract, Amount.of(65.5));
        assertEquals(amount2.subtract(amount1), Amount.of(-65.5));
    }

    @Test
    public void add_amount() throws TestFailError {
        var amount1 = Amount.of(100.0);
        var amount2 = Amount.of(34.5);
        Amount add = amount1.add(amount2);

        assertEquals(add, Amount.of(134.5));
        assertEquals(amount2.add(amount1), Amount.of(134.5));
    }

    @Test
    public void compare_amounts() throws TestFailError {
        var amount1 = Amount.of(100.0);
        var amount2 = Amount.of(34.5);

        assertEquals(amount1.compareTo(amount2), 1);
        assertEquals(amount1.compareTo(amount1), 0);
        assertEquals(amount2.compareTo(amount1), -1);
    }
}
