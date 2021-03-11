package com.example.account.controllers;

import com.example.account.controllers.models.AccountRequest;
import com.example.account.controllers.models.AccountResponse;
import com.example.account.controllers.models.OperationRequest;
import com.example.account.entities.Account;
import com.example.account.entities.Operation;
import com.example.account.exceptions.BusinessException;
import com.example.account.usecases.CreateAccount;
import com.example.account.usecases.DoOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final CreateAccount createAccount;
    private final DoOperation doOperation;

    public AccountController(CreateAccount createAccount, DoOperation doOperation) {
        this.createAccount = createAccount;
        this.doOperation = doOperation;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        try {
            Account account = createAccount.execute(accountRequest.getSaldo());
            return new ResponseEntity<>(new AccountResponse(account.getId(), account.getBalance()), HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable("id") String id,
            @RequestBody OperationRequest operationRequest) {
        try {
            Account account = doOperation.execute(Operation.valueOf(operationRequest.getOperacao()), operationRequest.getValor(), id);
            return new ResponseEntity<>(new AccountResponse(account.getId(), account.getBalance()), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
