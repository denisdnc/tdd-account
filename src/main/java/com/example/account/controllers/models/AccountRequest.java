package com.example.account.controllers.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AccountRequest {

    private Double saldo;

    @JsonCreator
    public AccountRequest(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
