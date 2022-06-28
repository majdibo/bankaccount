package com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer;

import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatement;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatements;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.printer.AccountStatementPrinter;

import java.io.IOException;
import java.io.OutputStream;

import static java.util.Optional.ofNullable;

public abstract class AbstractAccountStatementPrinter implements AccountStatementPrinter {
    protected OutputStream outputStream;

    public AbstractAccountStatementPrinter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void print(AccountStatements statements) throws IOException {
        String value = generateHeader();
        if (value != null) {
            byte[] bytes = value.getBytes();
            outputStream.write(bytes);
        }

        for (AccountStatement accountStatement : statements) {
            outputStream.write(generate(accountStatement).getBytes());
        }
    }

    protected abstract String generate(AccountStatement statement);
    protected abstract String generateHeader();
}
