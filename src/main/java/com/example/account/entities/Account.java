package com.example.account.entities;

import java.util.UUID;

public class Account {

    private String id;
    private double balance;

    public Account(double balance) {
        this.id = UUID.randomUUID().toString();
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}
