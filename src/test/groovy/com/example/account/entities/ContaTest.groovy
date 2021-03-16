package com.example.account.entities

import spock.lang.Specification

class ContaTest extends Specification {

    def "deve criar uma conta dado um saldo positivo e retornar o id gerado"() {
        given:
        def saldo = 1.0D

        when:
        def account = new Conta(saldo)

        then:
        account.id != null
        UUID.fromString(account.id.toString())
        account.saldo == 1.0D
    }

    def "deve criar uma conta dado um saldo negativo e retornar o id gerado"() {
        given:
        def saldo = -0.01D

        when:
        def account = new Conta(saldo)

        then:
        account.id != null
        UUID.fromString(account.id.toString())
        account.saldo == -0.01D
    }
}
