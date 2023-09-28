package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.common.JobRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean isAccountActive;

    public UserInfoUserDetails(String username, String password , List<JobRole> roles ,Boolean active){
        this.username = username;
        this.password = password;
        this.isAccountActive = active;
        this.authorities = roles.stream()
                .map((role)-> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountActive;
    }

    @Override
    public boolean isEnabled() {
        return isAccountActive;
    }
}
