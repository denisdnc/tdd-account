package com.example.account.usecases

import com.example.account.entities.Account
import com.example.account.entities.Operation
import com.example.account.gateways.AccountGateway
import spock.lang.Specification

class DoOperationTest extends Specification {

    /** dependencies */
    AccountGateway accountGateway = Mock()

    /** component to test */
    DoOperation doOperation = new DoOperation(accountGateway)

    def "saque de valor inferior ao saldo"() {
        given: "uma conta com saldo positivo"

        1 * accountGateway.findById("fake-account-id") >> new Account("fake-account-id", 100d)

        and: "um valor"

        double value = 50d

        and: "uma operação"

        Operation operation = Operation.DEBIT

        and: "um id da conta"

        String accountId = "fake-account-id"

        when: "eu executar a operação"

        Account result = doOperation.execute(operation, value, accountId)

        then: "deve salvar o saldo atualizado"

        1 * accountGateway.update(_ as Account) >> {
            Account arg ->
                assert arg.id == "fake-account-id"
                assert arg.balance == 50d
        }

        and: "deve retornar a conta atualizada"

        result.id == "fake-account-id"
        result.balance == 50d
    }

}
