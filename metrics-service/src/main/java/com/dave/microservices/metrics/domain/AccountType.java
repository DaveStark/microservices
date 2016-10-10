package com.dave.microservices.metrics.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public enum AccountType {

    Income(1, "Income"),
    Expense(2, "Expense");


    @Id
    private int id;

    @NotNull
    private String name;

    private AccountType(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
