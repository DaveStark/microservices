package com.dave.microservices.metrics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="account_types")
public enum AccountType implements Serializable {

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
