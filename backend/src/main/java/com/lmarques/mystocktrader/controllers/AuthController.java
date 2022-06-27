package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.User;
import com.lmarques.mystocktrader.repository.UserRepository;
import com.lmarques.mystocktrader.security.AccountCredentialsVO;
import com.lmarques.mystocktrader.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO account){
        try {
            var username = account.getLogin();
            var password = account.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Optional<User> user = userRepository.findByLogin(username);
            var token = "";

            if(user.isPresent()){
                System.out.println("User id:" + user.get().getId());
                token = jwtTokenProvider.createToken(username, user.get().getRoles());
            }
            else {
                throw new UsernameNotFoundException("Username"  + username + "not found!");
            }

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ok(response);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }
}
