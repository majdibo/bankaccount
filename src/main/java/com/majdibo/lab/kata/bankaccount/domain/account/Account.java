package com.majdibo.lab.kata.bankaccount.domain.account;

import com.majdibo.lab.kata.bankaccount.domain.Amount;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.*;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.printer.AccountStatementPrinter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.majdibo.lab.kata.bankaccount.domain.Amount.ZERO_AMOUNT;
import static java.util.Optional.ofNullable;

/**
 * Account defined by an id , a balance and a list of statements
 */
public class Account {

    private final UUID accountId;
    private final AccountStatements statements;
    private Amount balance;

    public Account(UUID accountId, AccountStatements statements) {
       this(accountId, ZERO_AMOUNT, statements);
    }

    public Account(UUID accountId, Amount initialBalance, AccountStatements statements) {
        this.accountId = accountId;
        this.balance = ofNullable(initialBalance).orElse(ZERO_AMOUNT);
        this.statements = statements;
    }

    public Amount getBalance() {
        return balance;
    }

    /**
     * deposit an {@link Amount} and log it on a specific date ({@link LocalDateTime})
     * @param date date of the deposit
     * @param amount amount of the deposit
     */
    public void deposit(LocalDateTime date, Amount amount) {
        AccountStatement statement = new DepositStatement(date, ofNullable(amount).orElse(ZERO_AMOUNT), balance);
        balance = statement.finalBalance();
        statements.add(statement);
    }

    /**
     * withdraw an amount from the account and log it on a specific date ({@link LocalDateTime})
     * @param date date of the withdrawal
     * @param amount amount of the withdrawal
     * @throws ExceedBalanceException if the amount to withdraw exceed the balance
     */
    public void withdraw(LocalDateTime date, Amount amount) throws ExceedBalanceException {
        final var amountToWithdraw = ofNullable(amount).orElse(ZERO_AMOUNT);
        if (amountToWithdraw.compareTo(balance) > 0) {
            throw new ExceedBalanceException(amountToWithdraw, balance);
        }

        AccountStatement statement = new WithdrawalStatement(date, amountToWithdraw, balance);
        balance = statement.finalBalance();
        statements.add(statement);
    }

    /**
     * print account history
     * @param statementPrinter {@link AccountStatementPrinter} to use
     * @throws IOException in case of error while printing
     */
    public void printStatements(AccountStatementPrinter statementPrinter) throws IOException {
        statementPrinter.print(statements);
    }

    /**
     * @return list of {@link AccountStatement} logged on this account
     */
    public List<AccountStatement> getAccountHistory() {
        return Collections.unmodifiableList(statements.findAll());
    }
}



