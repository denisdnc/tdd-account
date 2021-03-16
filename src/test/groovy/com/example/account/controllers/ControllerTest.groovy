package com.example.account.controllers

import com.example.account.controllers.models.ContaRequest
import com.example.account.controllers.models.ContaResponseBody
import com.example.account.entities.Conta
import com.example.account.exceptions.BusinessException
import com.example.account.exceptions.InvalidoSaldo
import com.example.account.usecases.CriarConta
import com.fasterxml.jackson.databind.ObjectMapper
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
class ControllerTest extends Specification {

    /** utils */
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @SpringBean
    CriarConta criarConta = Mock()

    /** dependencies */
//    @SpringBean

    def "Do POST on /account correctly"() {
        given: "the request body"
        ContaRequest contaRequest = new ContaRequest(123D)

        when: "do POST to register user"

        MvcResult result = mockMvc.perform(post("/api/v1/accounts")
                .content(objectMapper.writeValueAsString(contaRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then: "request token use case should be called and return"
        1 * criarConta.execute(123) >> new Conta(123)

        and: "status should be"

        result.response.status == HttpStatus.CREATED.value()

        and: "response body should match properties"
        ContaResponseBody contaResponseBody = objectMapper.readValue(result.response.contentAsString, ContaResponseBody.class)
        contaResponseBody.id
        contaResponseBody.saldo == 123
    }

    def "POST que retorna 422"() {
        given: "the request body"
        ContaRequest contaRequest = new ContaRequest(0D)

        when: "do POST to register user"

        MvcResult result = mockMvc.perform(post("/api/v1/accounts")
                .content(objectMapper.writeValueAsString(contaRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then: "request token use case should be called and return"
        1 * criarConta.execute(0D) >> {
            throw new BusinessException("Erro !")
        }

        and: "Status deve ser 422"

        result.response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
    }

}
