package com.majdibo.lab.kata.bankaccount.adapter.account.statement;

import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatement;
import com.majdibo.lab.kata.bankaccount.domain.account.statement.AccountStatements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class InMemoryAccountStatements implements AccountStatements {

    private List<AccountStatement> statementList = new ArrayList<>();

    @Override
    public Iterator<AccountStatement> iterator() {
        return statementList.iterator();
    }

    @Override
    public void forEach(Consumer<? super AccountStatement> action) {
        statementList.forEach(action);
    }

    @Override
    public Spliterator<AccountStatement> spliterator() {
        return statementList.spliterator();
    }

    @Override
    public void add(AccountStatement statement) {
        statementList.add(statement);
    }

    @Override
    public List<AccountStatement> findAll() {
        return statementList;
    }
}
