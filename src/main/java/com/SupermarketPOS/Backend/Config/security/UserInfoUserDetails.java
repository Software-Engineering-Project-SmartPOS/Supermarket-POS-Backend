package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
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
    private Boolean isAccountActive ;

//    public UserInfoUserDetails(Employee employee){
//        username = employee.getEmail();
//        password = employee.getPassword();
//        authorities = employee.getJobRole()
//    }

    public UserInfoUserDetails(String Username, String Password , List<JobRole> roles ,Boolean active){
        username = Username;
        password = Password;
        isAccountActive = active;
        System.out.println("inside the userDetails");
        System.out.println(roles.get(0).toString());
        authorities = roles.stream()
                .map((role)-> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());

//        authorities = List.of(new SimpleGrantedAuthority("ADMIN"));

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
