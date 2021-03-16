package com.example.account.usecases;

import com.example.account.entities.Conta;
import com.example.account.exceptions.InvalidoSaldo;
import com.example.account.exceptions.SaldoNegativo;
import com.example.account.gateways.ContaGateway;
import org.springframework.stereotype.Component;

@Component
public class CriarConta {

    private final ContaGateway contaGateway;

    public CriarConta(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta execute(double saldo) {
        // TODO
        if (saldo == 0) {
            throw new InvalidoSaldo("Olá, voce está zerado =(");
        }
        else if (saldo < 0) {
            throw new SaldoNegativo("Olá, seu saldo é negativo =(");
        }

        Conta conta = new Conta(saldo);
        this.contaGateway.save(conta);

        return conta;
    }



}
