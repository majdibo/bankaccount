package com.majdibo.lab.kata.bankaccount.domain.account.statement;

import com.majdibo.lab.kata.bankaccount.domain.Amount;

import java.time.LocalDateTime;

public class DepositStatement implements AccountStatement {
    private final LocalDateTime date;
    private final Amount amount;

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

    private final Amount balance;

    public DepositStatement(LocalDateTime date, Amount amount, Amount balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }


    @Override
    public Amount finalBalance() {
        return balance.add(amount);
    }

    @Override
    public AccountStatementType getType() {
        return AccountStatementType.DEPOSIT;
    }
}
