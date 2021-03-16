package com.example.account.entities;

import java.util.UUID;

public class Conta {

    private UUID id;
    private double saldo;

    public Conta(double saldo) {
        this.id = UUID.randomUUID();
        this.saldo = saldo;
    }

    public UUID getId() {
        return id;
    }

    public double getSaldo() {
        return this.saldo;
    }

}
