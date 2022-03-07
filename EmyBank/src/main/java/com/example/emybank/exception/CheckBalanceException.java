package com.example.emybank.exception;

public class CheckBalanceException extends SystemException{
    public CheckBalanceException(String message, String errorCode) {
        super(message, errorCode);
    }
}
