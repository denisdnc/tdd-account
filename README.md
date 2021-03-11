# Exercício TDD

## User story: Criação de uma conta

**Como**: novo cliente do banco  
**Eu quero**: criar uma conta  
**Para que**: possa movimentar meu dinheiro  

### Critérios de aceite:

#### Cenário: saldo positivo
**Dado** um saldo positivo  
**Quando** eu criar uma conta  
**Então** a conta deve ser criada com sucesso  

#### Cenário: saldo zero ou negativo
**Dado** um saldo igual ou menor que zero  
**Quando** eu criar uma conta  
**Então** a conta não deve ser criada

Resumo regras:
- receber o saldo inicial
- saldo deve ser maior que zero > 0

### Detalhamento técnico

- URL a ser criado: POST: /accounts  
```json
  {
    "saldo": 100.00
  }
```
Regras:
- salvar os dados num banco de dados
- id deve ser no formato UUID

Status para retonar:
- 201 em caso de sucesso
- 422 em caso de erros negócio
- response body deve ser:
```json
  {
    "id": "89ba0303-6a8b-40bc-b180-19300ea967c0",
    "saldo": 100.00
  }
```

## User story: Executar operação bancária

**Como**: cliente do banco  
**Eu quero**: executor operações de saque e depósito na minha conta  
**Para que**: possa movimentar meu dinheiro 

### Critérios de aceite:

#### Cenário 1: saque de valor inferior ao saldo
**Dado** uma conta com saldo positivo  
**E** um valor de saque inferior ao saldo  
**Quando** eu fizer o saque  
**Então** deve ser DEBITADO o valor do meu saldo  

#### Cenário 2: saque com valor superior ao saldo
**Dado** uma conta com um determinado saldo  
**E** um valor de saque maior que o saldo disponível  
**Quando** eu fizer o saque  
**Então** não deve ser debitado o valor da conta

#### Cenário 3: depósito
**Dado** uma conta  
**E** um valor de depósito  
**Quando** eu fizer o depósito  
**Então** deve ser CREDITADO o valor do meu saldo  

### Detalhamento técnico
URL a ser criado: PUT: /accounts/{id}
```json
  {
    "valor": 50.00,
    "operacao": "DEBIT"
  }
```
```json
  {
    "valor": 50.00,
    "operacao": "CREDIT"
  }
```
Regras:
- Valor do saque é obrigatório
- Tipo de operação é obrigatório
- salvar os dados num banco de dados

Status para retonar:
- 201 em caso de sucesso
- 422 em caso de dos campos obrigatórios não existirem
- response body de ser:

```json
  {
    "id": "89ba0303-6a8b-40bc-b180-19300ea967c0",
    "saldo": 50.00
  }
```