package com.majdibo.lab.kata.bankaccount;

import com.majdibo.lab.kata.bankaccount.adapter.account.statement.printer.impl.AccountStatementCsvPrinterTest;
import com.majdibo.lab.kata.bankaccount.core.test.TestRunner;
import com.majdibo.lab.kata.bankaccount.domain.AmountTest;
import com.majdibo.lab.kata.bankaccount.domain.account.AccountTest;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.DepositStatementTest;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.WithdrawalStatementTest;

public class TestMain {
    static  {
        TestRunner.tests.add(AccountTest.class);
        TestRunner.tests.add(AmountTest.class);
        TestRunner.tests.add(WithdrawalStatementTest.class);
        TestRunner.tests.add(DepositStatementTest.class);
        TestRunner.tests.add(AccountStatementCsvPrinterTest.class);

    }

    public static void main(String[] args) throws Throwable {
        TestRunner.run();
    }
}
