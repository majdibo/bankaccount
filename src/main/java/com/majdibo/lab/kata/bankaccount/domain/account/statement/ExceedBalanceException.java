package com.majdibo.lab.kata.bankaccount.domain.account.statement;

import com.majdibo.lab.kata.bankaccount.domain.Amount;

/**
 * Exception if Balance exceeded
 */
public class ExceedBalanceException extends Exception{
    public ExceedBalanceException(Amount amount, Amount balance) {
        super(String.format("Cannot withdraw %s , account balance is %s", amount, balance));
    }
}
