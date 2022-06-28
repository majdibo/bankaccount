package com.majdibo.lab.kata.bankaccount.domain.account.statement;


import com.majdibo.lab.kata.bankaccount.core.test.Test;
import com.majdibo.lab.kata.bankaccount.core.test.TestFailError;
import com.majdibo.lab.kata.bankaccount.core.test.Tests;
import com.majdibo.lab.kata.bankaccount.domain.Amount;

import java.time.LocalDateTime;

public class DepositStatementTest extends Tests {
    private static final String A_DATE_TIME_VALUE = "2022-06-28T00:00:00";


    @Test
    public void calculate_final_balance_after_deposit() throws TestFailError {
        var amount = new DepositStatement(LocalDateTime.parse(A_DATE_TIME_VALUE), Amount.of(15.0), Amount.of(100.0)).finalBalance();

        assertEquals(amount, Amount.of(115.0));
    }

    @Test
    public void deposit_null_amount() throws TestFailError {
        var amount = new DepositStatement(LocalDateTime.parse(A_DATE_TIME_VALUE), null, Amount.of(100.0)).finalBalance();

        assertEquals(amount, Amount.of(100.0));
    }

}
