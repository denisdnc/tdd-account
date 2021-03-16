package com.example.account.exceptions;

public class SaldoNegativo extends BusinessException {

   public SaldoNegativo(String message){
        super(message);
    }
}
