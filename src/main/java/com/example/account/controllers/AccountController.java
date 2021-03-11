package com.example.account.controllers;

import com.example.account.controllers.models.AccountRequest;
import com.example.account.controllers.models.AccountResponse;
import com.example.account.entities.Account;
import com.example.account.usecases.CreateAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final CreateAccount createAccount;

    public AccountController(CreateAccount createAccount) {
        this.createAccount = createAccount;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = createAccount.execute(accountRequest.getSaldo());
        return new ResponseEntity<>(new AccountResponse(account.getId(), account.getBalance()), HttpStatus.CREATED);
    }

}
