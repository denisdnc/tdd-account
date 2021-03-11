package com.example.account.entities

import spock.lang.Specification

class AccountTest extends Specification {

    def "saque de valor inferior ao saldo"() {

        given: "uma conta com saldo positivo"

        Account account = new Account(100d)

        when: "eu fizer o saque"

        account.debit(50d)

        then: "deve ser DEBITADO o valor do meu saldo"

        account.balance == 50d
    }

}
