package com.majdibo.lab.kata.bankaccount.domain.account.statement;

import java.util.List;

/**
 * a repository of account statements
 */
public interface AccountStatements extends Iterable<AccountStatement>{
    void add(AccountStatement statement);

    List<AccountStatement> findAll();
}
