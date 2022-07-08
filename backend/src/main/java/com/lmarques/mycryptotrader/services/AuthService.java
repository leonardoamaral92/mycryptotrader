package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Roles;
import com.lmarques.mycryptotrader.model.User;
import com.lmarques.mycryptotrader.model.authentication.Permission;
import com.lmarques.mycryptotrader.model.dto.AccountDTO;
import com.lmarques.mycryptotrader.model.dto.TokenDTO;
import com.lmarques.mycryptotrader.repository.InvestorRepository;
import com.lmarques.mycryptotrader.repository.PermissionRepository;
import com.lmarques.mycryptotrader.repository.UserRepository;
import com.lmarques.mycryptotrader.security.AccountCredentialsDTO;
import com.lmarques.mycryptotrader.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();


    public ResponseEntity signin(AccountCredentialsDTO account){
        try {
            var username = account.getLogin();
            var password = account.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Optional<User> user = userRepository.findByLogin(username);
            var tokenResponse = new TokenDTO();

            if(user.isPresent()){
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.get().getRoles());
            } else {
                throw new UsernameNotFoundException("Username"  + username + "not found!");
            }

            return ok(tokenResponse);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @Transactional
    public User signup(AccountDTO accountDTO) {
        User newUser = new User(accountDTO.getFullName(), accountDTO.getEmail(), passwordEncoder.encode(accountDTO.getPassword()));
        Permission permission = permissionRepository.findByDescription(Roles.COMMON_USER.name());
        newUser.addPermission(permission);

        return userRepository.save(newUser);
    }

    public ResponseEntity refreshToken(String username, String refreshToken){

        Optional<User> user = userRepository.findByLogin(username);
        var tokenResponse = new TokenDTO();

        if(user.isPresent()){
            tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username"  + username + "not found!");
        }

        return ok(tokenResponse);
    }
}
