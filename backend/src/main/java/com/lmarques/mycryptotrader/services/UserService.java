package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.User;
import com.lmarques.mycryptotrader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if(user.isPresent())
            return user.get();
        else
            throw new UsernameNotFoundException("Login " + username + "not found");
    }

    public boolean userExists(String email) {
        return userRepository.findByLogin(email).isPresent();
    }
}
