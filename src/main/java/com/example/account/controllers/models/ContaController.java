package com.example.account.controllers.models;

import com.example.account.entities.Conta;
import com.example.account.exceptions.BusinessException;
import com.example.account.usecases.CriarConta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    private final CriarConta criarConta;

    public ContaController(CriarConta criarConta) {
        this.criarConta = criarConta;
    }

    @PostMapping("/api/v1/accounts")
    public ResponseEntity<ContaResponseBody> postCriarConta(@RequestBody ContaRequest contaRequest) {
        try{
            Conta conta = criarConta.execute(contaRequest.getSaldo());
            ContaResponseBody contaResponseBody = new ContaResponseBody(conta.getId().toString(), conta.getSaldo());
            return new ResponseEntity(contaResponseBody, HttpStatus.CREATED);
        } catch (BusinessException e ){
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
