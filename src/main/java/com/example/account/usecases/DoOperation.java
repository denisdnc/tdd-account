package com.example.account.usecases;

import com.example.account.entities.Account;
import com.example.account.entities.Operation;
import com.example.account.gateways.AccountGateway;

public class DoOperation {

    private final AccountGateway accountGateway;

    public DoOperation(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(Operation operation, double value, String accountId) {
        Account account = accountGateway.findById(accountId);

        if (Operation.DEBIT.equals(operation)) {
            account.debit(value);
        }

        accountGateway.update(account);

        return account;
    }

}
