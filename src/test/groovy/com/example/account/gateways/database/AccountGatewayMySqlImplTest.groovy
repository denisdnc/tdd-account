package com.example.account.gateways.database

import com.example.account.entities.Account
import com.example.account.gateways.AccountGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.jdbc.SqlMergeMode
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

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

}
