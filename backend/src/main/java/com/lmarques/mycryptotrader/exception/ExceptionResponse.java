package com.lmarques.mycryptotrader.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionResponse implements Serializable {

    private Date timestamp;
    private String message;
    private String details;
}
