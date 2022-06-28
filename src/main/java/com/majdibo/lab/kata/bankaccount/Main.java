package com.majdibo.lab.kata.bankaccount;

import com.majdibo.lab.kata.bankaccount.adapter.account.statement.InMemoryAccountStatements;
import com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer.AccountStatementCsvPrinter;
import com.majdibo.lab.kata.bankaccount.domain.Amount;
import com.majdibo.lab.kata.bankaccount.domain.account.Account;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.ExceedBalanceException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Main {


    public static void main(String[] args) throws IOException {
        Account account = new Account(UUID.randomUUID(), new InMemoryAccountStatements());

        account.deposit(LocalDateTime.now(), Amount.of(100.0));
        account.deposit(LocalDateTime.now(), Amount.of(100.0));
        account.deposit(LocalDateTime.now(), Amount.of(100.0));
        try {
            account.withdraw(LocalDateTime.now(), Amount.of(100.0));
        } catch (ExceedBalanceException e) {
            e.printStackTrace();
        }

        account.printStatements(new AccountStatementCsvPrinter(System.out));
    }
}
