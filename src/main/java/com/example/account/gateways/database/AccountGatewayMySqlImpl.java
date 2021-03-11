package com.example.account.gateways.database;

import com.example.account.entities.Account;
import com.example.account.gateways.AccountGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountGatewayMySqlImpl implements AccountGateway {

    JdbcTemplate jdbcTemplate;

    public AccountGatewayMySqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Account account) {
        jdbcTemplate.update(
                "INSERT INTO accounts(id_conta, saldo) " +
                    "VALUES (?, ?);", account.getId(), account.getBalance());
    }
}
