package com.example.account.exceptions;

public class OperationNotAllowed extends BusinessException {
    public OperationNotAllowed(String message) {
        super(message);
    }
}
