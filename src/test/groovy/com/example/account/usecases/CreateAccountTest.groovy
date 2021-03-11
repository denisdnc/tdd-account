package com.example.account.usecases

import com.example.account.entities.Account
import com.example.account.exceptions.BusinessException
import com.example.account.gateways.AccountGateway
import spock.lang.Specification

class CreateAccountTest extends Specification {

    /** dependencies */
    AccountGateway accountGateway = Mock()

    /** component to test */
    CreateAccount createAccount = new CreateAccount(accountGateway)

    def "criar com saldo positivo"() {

        given: "um saldo positivo"

        double balance = 100.00

        when: "criar uma conta"

        Account result = createAccount.execute(balance)

        then: "a deve chamar o gateway para salvar"

        1 * accountGateway.save(_ as Account) >> { Account arg ->
            assert arg.balance == 100.00d
            assert arg.id != null
            UUID.fromString(arg.id)
        }

        and: "deve retornar a conta criada"

        result != null
        result.balance == 100.00d
        UUID.fromString(result.id)
    }

    def "criar com saldo zero"() {

        given: "um saldo zero"

        double balance = 0

        when: "criar uma conta"

        createAccount.execute(balance)

        then: "não deve chamar o gateway para salvar"

        0 * accountGateway.save(_ as Account)

        and: "validar saldo maior que zero"

        BusinessException e = thrown(BusinessException)
        e.message == "o saldo precisa ser maior que zero"
    }

    def "criar com saldo negativo"() {

        given: "um saldo zero"

        double balance = -1d

        when: "criar uma conta"

        createAccount.execute(balance)

        then: "não deve chamar o gateway para salvar"

        0 * accountGateway.save(_ as Account)

        and: "validar saldo maior que zero"

        BusinessException e = thrown(BusinessException)
        e.message == "o saldo precisa ser maior que zero"
    }

}
