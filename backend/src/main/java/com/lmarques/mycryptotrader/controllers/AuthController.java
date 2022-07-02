package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.security.AccountCredentialsVO;
import com.lmarques.mycryptotrader.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    @Operation(summary = "Authenticates a user and returns a token")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO account){
        if(validateParams(account))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authService.signin(account);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return token;
    }

    @PostMapping("/refresh/{username}")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken){
        if(validateParams(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = authService.refreshToken(username, refreshToken);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return token;
    }

    private boolean validateParams(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
    }

    private boolean validateParams(AccountCredentialsVO account) {
        return account == null || account.getLogin() == null || account.getLogin().isBlank()
                || account.getPassword() == null || account.getPassword().isBlank();
    }
}
