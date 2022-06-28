package com.majdibo.lab.kata.bankaccount.domain.account.statement.printer;

import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatements;

import java.io.IOException;

public interface AccountStatementPrinter {
    void print(AccountStatements statements) throws IOException;
}
