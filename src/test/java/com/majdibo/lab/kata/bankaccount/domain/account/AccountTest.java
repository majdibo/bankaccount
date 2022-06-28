package com.majdibo.lab.kata.bankaccount.domain.account;


import com.majdibo.lab.kata.bankaccount.core.test.Test;
import com.majdibo.lab.kata.bankaccount.core.test.TestFailError;
import com.majdibo.lab.kata.bankaccount.core.test.Tests;
import com.majdibo.lab.kata.bankaccount.domain.Amount;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatementType;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.ExceedBalanceException;
import com.majdibo.lab.kata.bankaccount.adapter.account.statement.InMemoryAccountStatements;

import static com.majdibo.lab.kata.bankaccount.domain.Amount.ZERO_AMOUNT;
import static java.time.LocalDateTime.parse;
import static java.util.UUID.fromString;

public class AccountTest extends Tests {

    private static final String A_DATE_TIME_VALUE = "2022-06-28T00:00:00";
    private static final String ACCOUNT_ID = "1afe86c5-0294-47b1-95d3-3579468cf41a";

    public AccountTest() {
    }

    @Test
    public void create_empty_account() throws TestFailError {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());
        assertEquals(account.getBalance(), ZERO_AMOUNT);
        assertEquals(account.getAccountHistory().isEmpty(), true);
    }

    @Test
    public void make_a_deposit_in_account() throws TestFailError {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());
        assertEquals(account.getBalance(), ZERO_AMOUNT);

        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(100.0));

        assertEquals(account.getBalance(), Amount.of(100.0));
    }

    @Test
    public void make_a_deposit_in_account_with_null_amount() throws TestFailError {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());
        assertEquals(account.getBalance(), ZERO_AMOUNT);

        account.deposit(parse(A_DATE_TIME_VALUE), null);

        assertEquals(account.getBalance(),ZERO_AMOUNT);

        assertEquals(account.getAccountHistory().size(), 1);
    }

    @Test
    public void make_a_withdrawal_from_account() throws TestFailError, ExceedBalanceException {
        Account account = new Account(fromString(ACCOUNT_ID), Amount.of(100.0), new InMemoryAccountStatements());
        assertEquals(account.getBalance(), Amount.of(100.0));

        account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(70.0));

        assertEquals(account.getBalance(), Amount.of(30.0));
    }


    @Test
    public void expect_error_when_withdrawing_from_empty_account() throws TestFailError {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());

        try {
            account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(200.0));
        } catch (ExceedBalanceException e) {
            return;
        }

        throw new TestFailError(ExceedBalanceException.class.getName() + " expected");

    }

    @Test
    public void make_a_withdrawal_from_account_that_exceed_balance() throws TestFailError {
        Account account = new Account(fromString(ACCOUNT_ID), Amount.of(100.0), new InMemoryAccountStatements());
        assertEquals(account.getBalance(), Amount.of(100.0));

        try {
            account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(200.0));
        } catch (ExceedBalanceException e) {
            return;
        }

        throw new TestFailError(ExceedBalanceException.class.getName() + " expected");

    }


    @Test
    public void record_account_statement() throws TestFailError, ExceedBalanceException {
        Account account = new Account(fromString(ACCOUNT_ID), new InMemoryAccountStatements());

        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(70.0));
        account.deposit(parse(A_DATE_TIME_VALUE), Amount.of(70.0));
        account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(70.0));
        account.withdraw(parse(A_DATE_TIME_VALUE), Amount.of(70.0));

        assertEquals(account.getAccountHistory().size(), 4);
        assertEquals(account.getAccountHistory().get(0).getType(), AccountStatementType.DEPOSIT);
        assertEquals(account.getAccountHistory().get(3).getType(), AccountStatementType.WITHDRAWAL);
    }


}
