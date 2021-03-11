package com.example.account.controllers

import com.example.account.controllers.models.AccountRequest
import com.example.account.controllers.models.AccountResponse
import com.example.account.entities.Account
import com.example.account.usecases.CreateAccount
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.catalina.User
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class AccountControllerTest extends Specification {

    /** utils */
    @Autowired
    MockMvc mockMvc
    @Autowired
    ObjectMapper objectMapper

    /** dependencies */
    @SpringBean
    CreateAccount createAccount = Mock()

    def "Register User correctly"() {
        given: "the request body"

        AccountRequest accountRequest = new AccountRequest(100d)

        when: "do POST to register user"

        MvcResult result = mockMvc.perform(post("/accounts")
                .content(objectMapper.writeValueAsString(accountRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then: "request token use case should be called and return"

        1 * createAccount.execute(100d) >> new Account(100d)

        and: "status should be"

        result.response.status == HttpStatus.CREATED.value()

        and: "response body should match properties"

        AccountResponse responseBody = objectMapper.readValue(result.response.contentAsString, AccountResponse.class)

        responseBody.id != null
        responseBody.saldo == 100d
    }
}
