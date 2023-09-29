package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.Owner;
import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.OwnerRepository;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OwnerRepository ownerRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("fffffffffffffffffff");
//        Optional<Employee> employee1 = employeeRepository.findByEmail(username);
//        System.out.println("jjjjjjjjjjjjj");
//        System.out.println(employee1);
//        return employee1.map(employee -> new UserInfoUserDetails(
//                        employee.getEmail(),
//                        employee.getPassword(),
//                        Collections.singletonList(employee.getJobRole()),
//                        employee.getActive()))
//                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<Owner> owner = ownerRepository.findByEmail(username);
        System.out.println("searched for the owner");
        System.out.println(owner);

        Optional<Employee> employee = employeeRepository.findByEmail(username);
        System.out.println(employee);
//        System.out.println(owner.get());
        System.out.println(owner.isPresent());
        System.out.println(employee.isPresent());

//        if(true){

        if (owner.isPresent()) {
            Owner ownerUser = owner.get();
            System.out.println("'im in the owner");
            return new UserInfoUserDetails(
                    ownerUser.getEmail(),
                    ownerUser.getPassword(),
                    Collections.singletonList(JobRole.OWNER), // Assuming Owner has a role field
                    true // You can set the active status as needed
            );
        } else if (employee.isPresent()) {
            Employee employeeUser = employee.get();
            System.out.println("'im in the employee");
            return new UserInfoUserDetails(
                    employeeUser.getEmail(),
                    employeeUser.getPassword(),
                    Collections.singletonList(employeeUser.getJobRole()),
                    employeeUser.getActive()
            );
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
