package com.example.account.entities;

import com.example.account.exceptions.OperationNotAllowed;

import java.util.UUID;

public class Account {

    private final String id;
    private double balance;

    public Account(double balance) {
        this(UUID.randomUUID().toString(), balance);
    }

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void debit(double value) {
        if (value > balance) {
            throw new OperationNotAllowed("valor maior que saldo");
        }

        balance = balance - value;
    }

    public void credit(double value) {
        balance = balance + value;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}
