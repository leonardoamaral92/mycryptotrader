package com.lmarques.mycryptotrader.model;

import com.lmarques.mycryptotrader.model.authentication.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ct_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String fullName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ct_user_permission", joinColumns = { @JoinColumn(name = "id_user") },
        inverseJoinColumns = { @JoinColumn(name = "id_permission") } )
    private Set<Permission> permissions = new HashSet<>();

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.login = email;
        this.password = password;
        this.permissions = permissions;
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        permissions.forEach(permission -> roles.add(permission.getDescription()));
        return roles;
    }

    public void addPermission(Permission permission){
        permissions.add(permission);
    }

    @OneToOne(mappedBy = "user")
    private Investor investor;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getUsername() {
        return this.login;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
