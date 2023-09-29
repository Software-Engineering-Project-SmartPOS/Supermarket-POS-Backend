package com.SupermarketPOS.Backend.controller.common;

import com.SupermarketPOS.Backend.Config.security.JwtService;
import com.SupermarketPOS.Backend.dto.AuthRequest;
import com.SupermarketPOS.Backend.dto.OwnerInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.model.Owner;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.service.OwnerService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final EmployeeService employeeService;
    private final OwnerService ownerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(EmployeeService employeeService, EmployeeService employeeService1, OwnerService ownerService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService1;
        this.ownerService = ownerService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/getToken")
        public String getToken(@RequestBody String username){
        return jwtService.generateToken(username);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        if (authentication.isAuthenticated()){
            return  jwtService.generateToken(authRequest.getUsername());
        }
        else{
            throw new UsernameNotFoundException("invalid user request");
        }
    }

    @PostMapping("/RegisterOwner")
    public String registerOwner(@RequestBody OwnerInput ownerInput ){
        Owner Owner = ownerService.addOwner(ownerInput) ;
        return "Owner added";

    }

    @PostMapping("/addEmployee")
    public Employee addANewEmployee(@RequestBody EmployeeInput employeeInput){
        return  employeeService.AddNewEmployee(employeeInput);

    }

    @GetMapping("/getEmployee")
    public Employee getEmployee(@RequestParam int id) {
        // Use the "id" query parameter to retrieve the employee
        return employeeService.findById(id);
    }

}
