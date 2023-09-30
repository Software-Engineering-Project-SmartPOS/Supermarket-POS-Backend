package com.SupermarketPOS.Backend.controller.common;

import com.SupermarketPOS.Backend.Config.security.JwtService;
import com.SupermarketPOS.Backend.dto.AuthRequest;
import com.SupermarketPOS.Backend.dto.OwnerInput;
import com.SupermarketPOS.Backend.dto.common.AuthPayload;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.model.Owner;
import com.SupermarketPOS.Backend.service.OwnerService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//used this controller to map new Owner addition and authenticate(sending token)
@RestController
@Controller
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
    //use this endpoint to get the token when login
//    @PostMapping("/authenticate")
//    public String AuthenticateUser(@RequestBody AuthRequest authRequest){
    @QueryMapping
    public AuthPayload AuthenticateUser(@Argument AuthRequest authRequest){
        // authenticate the userDetails in the authRequest
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        System.out.println("At the authenticate and get token function");
        if (authentication.isAuthenticated()){
            //sending the jwt token if user is authenticated
            String token = jwtService.generateToken(authRequest.getUsername());
            return  new AuthPayload(
                    token,
                    employeeService.getByEmail(authRequest.getUsername())
            );
        }
        else{
            throw new UsernameNotFoundException("invalid user request");
        }
    }



    @MutationMapping
    public String registerOwner(@Argument EmployeeInput ownerDetail){
        employeeService.AddNewEmployee(ownerDetail) ;
        return "Owner added";
    }






//    @PostMapping("/getToken")
    //this is used to register/ add new owner to the database
//    @PostMapping("/RegisterOwner")
//    public String registerOwner(@RequestBody OwnerInput ownerInput ){
//        Owner Owner = ownerService.addOwner(ownerInput) ;
//        return "Owner added";
//    }
//        public String getToken(@RequestBody String username){
//        return jwtService.generateToken(username);
//    }

//    @PostMapping("/addEmployee")
//    public Employee addANewEmployee(@RequestBody EmployeeInput employeeInput){
//        return  employeeService.AddNewEmployee(employeeInput);
//
//    }
//
//    @GetMapping("/getEmployee")
//    public Employee getEmployee(@RequestParam int id) {
//        // Use the "id" query parameter to retrieve the employee
//        return employeeService.findById(id);
//    }
//
//
//    @GetMapping("/getOwner")
//    public Owner getOwner(@RequestParam String username) {
//        return ownerService.findOwnerByEmail(username);
//    }

}
