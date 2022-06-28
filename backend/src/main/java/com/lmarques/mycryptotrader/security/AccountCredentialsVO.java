package com.lmarques.mycryptotrader.security;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AccountCredentialsVO implements Serializable {

    private String login;
    private String password;
}
