package com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer.impl;


import com.majdibo.lab.kata.bankaccount.adapter.account.statement.InMemoryAccountStatements;
import com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer.AccountStatementCsvPrinter;
import com.majdibo.lab.kata.bankaccount.core.test.Test;
import com.majdibo.lab.kata.bankaccount.core.test.TestFailError;
import com.majdibo.lab.kata.bankaccount.core.test.Tests;
import com.majdibo.lab.kata.bankaccount.domain.Amount;
import com.majdibo.lab.kata.bankaccount.domain.account.Account;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.ExceedBalanceException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.time.LocalDateTime.parse;
import static java.util.UUID.fromString;

public class AccountStatementCsvPrinterTest extends Tests {
    private static final String A_DATE_TIME_VALUE = "2022-06-28T00:00:00";
    private static final String ACCOUNT_ID = "1afe86c5-0294-47b1-95d3-3579468cf41a";


    public AccountStatementCsvPrinterTest() {
    }

    @Test
    public void print_statements_of_account() throws TestFailError, ExceedBalanceException, IOException {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());

        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(100.0));
        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(100.0));
        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(100.0));
        account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(100.0));

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            AccountStatementCsvPrinter statementPrinter = new AccountStatementCsvPrinter(stream);
            account.printStatements(statementPrinter);

            String statementsAsString = statementPrinter.getOutputStream().toString();

            final String expectedStatemensString = "operation,date,amount,balance\n" +
                    "DEPOSIT,2022-06-28T00:00,100.0,100.0\n" +
                    "DEPOSIT,2022-06-28T00:00,100.0,200.0\n" +
                    "DEPOSIT,2022-06-28T00:00,100.0,300.0\n" +
                    "WITHDRAWAL,2022-06-28T00:00,100.0,200.0\n";

            assertEquals(statementsAsString, expectedStatemensString);

        }

    }

    @Test
    public void print_statements_of_account_with_no_movements() throws TestFailError, ExceedBalanceException, IOException {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            AccountStatementCsvPrinter statementPrinter = new AccountStatementCsvPrinter(stream);
            account.printStatements(statementPrinter);

            String statementsAsString = statementPrinter.getOutputStream().toString();

            final String expectedStatemensString = "operation,date,amount,balance\n";

            assertEquals(statementsAsString, expectedStatemensString);

        }
    }

}