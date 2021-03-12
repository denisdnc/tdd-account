package com.example.account.gateways.database


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.jdbc.SqlMergeMode
import org.springframework.transaction.annotation.Transactional
import spock.lang.Ignore
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
@Ignore
class GatewayMySqlImplTest extends Specification {

    /** utils */
    @Autowired
    JdbcTemplate jdbcTemplate

    /** component to test */
    @Autowired
//    Gateway accountGateway

    @Transactional
    def "test"() {
        given: ""

        when: ""

        then: ""

//        Integer result = jdbcTemplate.queryForObject(
//                String.format("SELECT COUNT(*) AS cherere FROM astarara a " +
//                        "WHERE tururu = '%s';", "var"), Integer.class)

        result == 1
    }

}
