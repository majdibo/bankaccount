package com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer;

import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatement;

import java.io.OutputStream;

public class AccountStatementCsvPrinter extends AbstractAccountStatementPrinter {
    public AccountStatementCsvPrinter(OutputStream outputStream) {
        super(outputStream);
    }

    protected String generate(AccountStatement statement) {
        return String.format("%s,%s,%s,%s\n",
                statement.getType(),
                statement.getDateTime(),
                statement.getAmount(),
                statement.finalBalance()
        );
    }

    @Override
    protected String generateHeader() {
        return "operation,date,amount,balance\n";
    }
}
