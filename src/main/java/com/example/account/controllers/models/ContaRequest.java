package com.example.account.controllers.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ContaRequest {

    private Double saldo;

    @JsonCreator
    public ContaRequest(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo(){
        return saldo;
    }
}
