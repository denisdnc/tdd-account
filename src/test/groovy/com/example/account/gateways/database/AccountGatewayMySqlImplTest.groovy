package com.example.account.gateways.database

import com.example.account.entities.Account
import com.example.account.gateways.AccountGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.jdbc.SqlMergeMode
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import java.sql.ResultSet

@SpringBootTest
@AutoConfigureTestDatabase
@SqlGroup([
        @Sql(scripts = "classpath:scripts/ddl_create.sql"),
        @Sql(scripts = "classpath:scripts/ddl_drop.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
])
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@ActiveProfiles(value = "test")
class AccountGatewayMySqlImplTest extends Specification {

    /** utils */
    @Autowired
    JdbcTemplate jdbcTemplate

    /** component to test */
    @Autowired
    AccountGateway accountGateway

    @Transactional
    def "deve criar registro com sucesso"() {
        given: "um conta"

        Account account = new Account(100d)

        when: "salvar"

        accountGateway.save(account)

        then: "o registro deve estar no banco de dados"

        Integer result = jdbcTemplate.queryForObject(
                String.format("SELECT COUNT(*) AS quantity FROM accounts a " +
                        "WHERE id_conta = '%s';", account.id), Integer.class)

        result == 1
    }

    @Transactional
    @Sql(scripts = "classpath:scripts/dml.sql")
    def "deve buscar por id com sucesso"() {
        when: "buscar por id"

        Account account = accountGateway.findById("89ba0303-6a8b-40bc-b180-19300ea967c0")

        then: "deve conter as seguintes propriedades"

        account != null
        account.balance == 1000d
        account.id == "89ba0303-6a8b-40bc-b180-19300ea967c0"
    }

    @Transactional
    def "deve buscar por id com resultado vazio"() {
        when: "buscar por id"

        Account account = accountGateway.findById("89ba0303-6a8b-40bc-b180-19300ea967c0")

        then: "deve conter as seguintes propriedades"

        account == null
    }

    @Transactional
    @Sql(scripts = "classpath:scripts/dml.sql")
    def "fazer update com sucesso"() {
        given: "um conta"

        Account account = new Account("89ba0303-6a8b-40bc-b180-19300ea967c0", 500d)

        when: "atualizar por id"

        accountGateway.update(account)

        then: "o registro deve estar no banco de dados atualizado"

        RowMapper<Account> extractor = { ResultSet resultSet, i -> new Account(resultSet.getString("id_conta"), resultSet.getDouble("saldo")) }

        List<Account> updatedRecords = jdbcTemplate.query(
                "SELECT id_conta, saldo FROM accounts WHERE id_conta = ?",
                extractor, account.id);

        updatedRecords.size() == 1
        updatedRecords[0].balance == 500d
    }

}
