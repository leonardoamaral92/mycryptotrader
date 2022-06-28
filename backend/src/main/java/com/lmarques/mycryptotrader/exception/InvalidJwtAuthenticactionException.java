package com.lmarques.mycryptotrader.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticactionException extends AuthenticationException {

    public InvalidJwtAuthenticactionException(String exception){
        super(exception);
    }
}
