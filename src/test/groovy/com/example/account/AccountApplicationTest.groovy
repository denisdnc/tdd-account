package com.example.account

import com.example.account.AccountApplication
import spock.lang.Specification

class AccountApplicationTest extends Specification {

    def "contextLoads"() {
        when: "start spring boot application"

        AccountApplication.main()

        then: "should not throw error"

        notThrown(Exception)
    }

}
