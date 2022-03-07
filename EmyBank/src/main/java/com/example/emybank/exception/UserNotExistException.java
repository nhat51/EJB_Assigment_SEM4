package com.example.emybank.exception;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends BusinessException{
    public UserNotExistException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserNotExistException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
