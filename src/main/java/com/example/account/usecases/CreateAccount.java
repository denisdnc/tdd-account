package com.example.account.usecases;

import com.example.account.entities.Account;
import com.example.account.exceptions.BusinessException;
import com.example.account.gateways.AccountGateway;

public class CreateAccount {

    private final AccountGateway accountGateway;

    public CreateAccount(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(double balance) {

        if (balance <= 0) {
            throw new BusinessException("o saldo precisa ser maior que zero");
        }

        Account account = new Account(balance);
        accountGateway.save(account);

        return account;
    }
}
