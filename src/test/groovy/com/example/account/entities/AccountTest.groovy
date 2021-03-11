package com.example.account.entities

import com.example.account.exceptions.OperationNotAllowed
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

    def "saque de valor superior ao saldo"() {

        given: "uma conta com saldo positivo"

        Account account = new Account(100d)

        when: "fizer um saque com valor maior que o saldo disponível"

        account.debit(150d)

        then: "não deve ser DEBITADO o valor do meu saldo"

        OperationNotAllowed e = thrown(OperationNotAllowed)
        e.message == "valor maior que saldo"
    }

    def "executar deposito"() {

        given: "uma conta com saldo"

        Account account = new Account(50d)

        when: "fizer um depósito"

        account.credit(100d)

        then: "não deve ser CREDITADO o valor do meu saldo"

        account.balance == 150d
    }

}
