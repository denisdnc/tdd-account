package com.example.account.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Ignore
class ControllerTest extends Specification {

    /** utils */
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    /** dependencies */
//    @SpringBean

    def "Do POST on /something correctly"() {
        given: "the request body"

        // TODO

        when: "do POST to register user"

        MvcResult result = mockMvc.perform(post("/something")
//                .content(objectMapper.writeValueAsString(accountRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then: "request token use case should be called and return"

        //

        and: "status should be"

//        result.response.status == HttpStatus.CREATED.value()

        and: "response body should match properties"

//        AccountResponse responseBody = objectMapper.readValue(result.response.contentAsString, AccountResponse.class)
    }
}
