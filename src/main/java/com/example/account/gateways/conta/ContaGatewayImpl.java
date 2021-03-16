package com.example.account.gateways.conta;

import com.example.account.entities.Conta;
import com.example.account.gateways.ContaGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContaGatewayImpl implements ContaGateway {

    private final JdbcTemplate jdbcTemplate;

    public ContaGatewayImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Conta conta) {
        jdbcTemplate.update("INSERT INTO accounts (id_conta, saldo) VALUES (?, ?)", conta.getId().toString(), conta.getSaldo());
    }
}
