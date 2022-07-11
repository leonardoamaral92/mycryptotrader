package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.model.User;
import com.lmarques.mycryptotrader.model.dto.AccountDTO;
import com.lmarques.mycryptotrader.security.AccountCredentialsDTO;
import com.lmarques.mycryptotrader.services.AuthService;
import com.lmarques.mycryptotrader.services.InvestorService;
import com.lmarques.mycryptotrader.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    InvestorService investorService;
    @Autowired
    UserService userService;

    @Value("${msg.response.invalid-request}")
    String MSG_INVALID_REQUEST;

    @PostMapping("/signin")
    @Operation(summary = "Authenticates a user and returns a token")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO account){
        if(isNotValidParams(account))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MSG_INVALID_REQUEST);

        var token = authService.signin(account);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MSG_INVALID_REQUEST);

        return token;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody AccountDTO account){
        if(isNotValidAccountParams(account))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MSG_INVALID_REQUEST);

        boolean userExists = userService.userExists(account.getEmail());
        if(userExists)
            return ResponseEntity.badRequest().body("Usuário já existente.");

        User user = authService.signup(account);
        investorService.create(user);

        return ResponseEntity.ok(account);
    }

    @PostMapping("/refresh/{username}")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken){
        if(!isNotValidParams(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MSG_INVALID_REQUEST);
        var token = authService.refreshToken(username, refreshToken);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MSG_INVALID_REQUEST);

        return token;
    }
    private boolean isNotValidParams(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
    }

    private boolean isNotValidParams(AccountCredentialsDTO account) {
        return account == null || account.getLogin() == null || account.getLogin().isBlank()
                || account.getPassword() == null || account.getPassword().isBlank();
    }

    private boolean isNotValidAccountParams(AccountDTO account) {
        return account == null || account.getEmail() == null || account.getEmail().isBlank()
                || account.getPassword() == null || account.getPassword().isBlank()
                || account.getFullName() == null || account.getFullName().isBlank();
    }
}
