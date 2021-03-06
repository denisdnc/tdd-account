package com.example.account.gateways;

import com.example.account.entities.Account;

public interface AccountGateway {
    void save(Account account);
    void update(Account account);
    Account findById(String id);
}
