package com.majdibo.lab.kata.bankaccount.domain.account.statement;

import com.majdibo.lab.kata.bankaccount.domain.Amount;

import java.time.LocalDateTime;

/**
 * an Account statement defined by its type and date-time
 */
public interface AccountStatement {
    LocalDateTime getDateTime();

    Amount getAmount();

    /**
     * @return balance before statement
     */
    Amount getBalance();

    /**
     * @return resulting balance after applying statement
     */
    Amount finalBalance();

    AccountStatementType getType();
}
