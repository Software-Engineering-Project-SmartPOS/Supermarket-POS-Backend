package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findByEmail(username);
        return employee1.map(employee -> new UserInfoUserDetails(employee.getEmail(),
                employee.getPassword(),
                Collections.singletonList(employee.getJobRole()),
                employee.getActive()))
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

    }
}
