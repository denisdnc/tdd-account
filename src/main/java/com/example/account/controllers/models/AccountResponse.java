package com.example.account.controllers.models;

public class AccountResponse {

    private String id;
    private Double saldo;

    public AccountResponse(String id, Double saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }
}
