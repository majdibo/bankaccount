package com.majdibo.lab.kata.bankaccount.domain.account.statement;

import com.majdibo.lab.kata.bankaccount.domain.Amount;

import java.time.LocalDateTime;

public class WithdrawalStatement implements AccountStatement {
    private final LocalDateTime date;
    private final Amount amount;
    private final Amount balance;

    @Override
    public LocalDateTime getDateTime() {
        return date;
    }

    @Override
    public Amount getAmount() {
        return amount;
    }

    @Override
    public Amount getBalance() {
        return balance;
    }

    public WithdrawalStatement(LocalDateTime date, Amount amount, Amount balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }


    @Override
    public Amount finalBalance() {
        return balance.subtract(amount);
    }

    @Override
    public AccountStatementType getType() {
        return AccountStatementType.WITHDRAWAL;
    }
}
