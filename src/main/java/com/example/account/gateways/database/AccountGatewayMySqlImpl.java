package com.example.account.gateways.database;

import com.example.account.entities.Account;
import com.example.account.gateways.AccountGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class AccountGatewayMySqlImpl implements AccountGateway {

    private final JdbcTemplate jdbcTemplate;

    public AccountGatewayMySqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Account account) {
        jdbcTemplate.update(
                "INSERT INTO accounts(id_conta, saldo) " +
                        "VALUES (?, ?);", account.getId(), account.getBalance());
    }

    @Override
    public void update(Account account) {
        jdbcTemplate.update(
                "UPDATE accounts SET saldo = ? WHERE id_conta = ? ", account.getBalance(), account.getId());
    }

    @Override
    public Account findById(String id) {
        List<Account> query = jdbcTemplate.query(
                "SELECT id_conta, saldo FROM accounts WHERE id_conta = ?",
                (resultSet, i) -> new Account(resultSet.getString("id_conta"), resultSet.getDouble("saldo")), id);

        if (CollectionUtils.isEmpty(query)) {
            return null;
        }

        return query.get(0);
    }
}
