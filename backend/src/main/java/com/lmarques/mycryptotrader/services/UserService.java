package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Roles;
import com.lmarques.mycryptotrader.model.User;
import com.lmarques.mycryptotrader.model.authentication.Permission;
import com.lmarques.mycryptotrader.model.dto.InvestorDTO;
import com.lmarques.mycryptotrader.repository.PermissionRepository;
import com.lmarques.mycryptotrader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;
    PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if(user.isPresent())
            return user.get();
        else
            throw new UsernameNotFoundException("Login " + username + "not found");
    }

    public User create(String fullName, String email, String password, Set<String> permissionSet){
        User user = new User(fullName, email, passwordEncoder.encode(password));
        permissionSet.forEach(description -> {
            Permission permission = permissionRepository.findByDescription(description);
            user.addPermission(permission);
        });

        return userRepository.save(user);
    }
}
