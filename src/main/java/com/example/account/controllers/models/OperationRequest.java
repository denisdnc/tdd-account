package com.example.account.controllers.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class OperationRequest {

    private Double valor;
    private String operacao;

    @JsonCreator
    public OperationRequest(Double valor, String operacao) {
        this.valor = valor;
        this.operacao = operacao;
    }

    public Double getValor() {
        return valor;
    }

    public String getOperacao() {
        return operacao;
    }
}
