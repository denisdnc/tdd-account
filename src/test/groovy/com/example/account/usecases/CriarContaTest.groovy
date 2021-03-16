package com.example.account.usecases

import com.example.account.entities.Conta
import com.example.account.exceptions.InvalidoSaldo
import com.example.account.exceptions.SaldoNegativo
import com.example.account.gateways.ContaGateway
import spock.lang.Specification

class CriarContaTest extends Specification {

    ContaGateway contaGateway = Mock()
    CriarConta criarConta

    void setup() {
        criarConta = new CriarConta(contaGateway)
    }

    def "não deve criar uma conta dado um saldo zero"() {
        given: "dado um saldo igual a 0"
        double saldo = 0.0D

        when: "quando criar a conta com saldo"
        criarConta.execute(saldo)

        then: "entao não deve criar a conta"

        def bla = thrown(InvalidoSaldo)
        bla.message == "Olá, voce está zerado =("

        and: "Nao chama bd"
        0 * contaGateway.save(_ as Conta)
    }

    def "não deve criar uma conta dado um saldo negativo"() {
        given: "dado um saldo menor que 0"
        double saldo = -5.0D

        when: "quando criar a conta com saldo"
        criarConta.execute(saldo)

        then: "entao não deve criar a conta"

        def exception = thrown(SaldoNegativo)
        exception.message == "Olá, seu saldo é negativo =("

        and: "Nao chama bd"
        0 * contaGateway.save(_ as Conta)
    }

    def "A conta deve ser criada corretamente"(){
        given: "Dado um saldo positivo "
        double saldo = 546D

        when: "quando criar a conta com o saldo positivo"
        Conta conta = criarConta.execute(saldo)

        then:"A conta deve ser criada com o saldo belezinha"
        conta
        conta.id
        conta.saldo == 546D

        and: "deve salvar no banco de dados"
        1 * contaGateway.save(_ as Conta) >> {
            Conta arg ->
                 arg.id != null
                 arg.saldo == 546D
        }
    }

}
