package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //getting the owner and employee by the username
        Optional<Employee> employee = employeeRepository.findByEmail(username);

        //return useDetail objects according to the user(employee)

        if (employee.isPresent()) {
            Employee employeeUser = employee.get();
            System.out.println(employeeUser.getJobRole());

//            return a userDetails with the Employee details
            return User
                    .withUsername(employeeUser.getEmail())
                    .password(employeeUser.getPassword())
                    .roles(employeeUser.getJobRole().toString())
                    .accountExpired(!employeeUser.getActive())
                    .accountLocked(!employeeUser.getActive())
                    .credentialsExpired(!employeeUser.getActive())
                    .build();

        } else {
            throw new UsernameNotFoundException("User not found");
        }




//        Optional<Owner> owner = ownerRepository.findByEmail(username);
//        if (owner.isPresent()) {
//            Owner ownerUser = owner.get();
//
////            return a userDetails with the Owner details
//            System.out.println(ownerUser);
//            return User
//                    .withUsername(ownerUser.getEmail())
//                    .password(ownerUser.getPassword())
//                    .roles("OWNER")
//                    .accountExpired(false)
//                    .accountLocked(false)
//                    .credentialsExpired(false)
//                    .build();
//
//
//        } else
    }
}
