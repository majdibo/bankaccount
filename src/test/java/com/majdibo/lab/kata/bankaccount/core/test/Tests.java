package com.majdibo.lab.kata.bankaccount.core.test;

import java.util.Objects;

public abstract class Tests {

    public <T> void assertEquals(T actual, T expected) throws TestFailError {
        assertEquals(actual, expected, String.format(this.getClass().toString()+": expected : %s, actual : %s", expected, actual));
    }


    public <T> void assertEquals(T actual, T expected, String message) throws TestFailError {
        if (!Objects.equals(actual, expected)) {
            throw new TestFailError(this.getClass().toString()+": "+message);
        }
    }



}
