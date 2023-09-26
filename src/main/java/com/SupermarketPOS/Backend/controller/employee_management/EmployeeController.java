package com.SupermarketPOS.Backend.controller.employee_management;


import com.SupermarketPOS.Backend.Config.security.JwtService;
import com.SupermarketPOS.Backend.dto.employee_management.*;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController

public class EmployeeController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmployeeService employeeService;
    private final EmployeeOutputMapper employeeOutputMapper;



    @PostMapping("/authenticate")
    public String loginEmployee(@RequestBody LoginDetails loginDetails){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(),loginDetails.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(loginDetails.getUsername());
        }
        else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }


    @Autowired
    public EmployeeController(EmployeeService employeeService ,
                              EmployeeOutputMapper employeeOutputMapper,
                              JwtService jwtService,
                              AuthenticationManager authenticationManager){
        this.employeeService = employeeService;
        this.employeeOutputMapper = employeeOutputMapper;
        this.jwtService = jwtService;
        this.authenticationManager =authenticationManager;
    }

    //get all employees
    @QueryMapping
    public List<Employee> allEmployees(){
        return employeeService.getAllEmployees();
    }





    //add a new employee
    @MutationMapping
    public Employee AddEmployee(@Argument EmployeeInput employeeInput){
        return employeeService.AddNewEmployee(employeeInput);
    }

    @QueryMapping
    // this validate the given input and send a validation report back
    public EmployeeValidationReport isValidateEmployee(@Argument EmployeeInput employeeInput ){
        return employeeService.Validate(employeeInput);
    };

    @SchemaMapping(typeName = "Employee" , field = "Branch")
    public Branch getEmployeeBranch(Employee employee){
        return employeeService.getBranchByEmployeeId(employee.getId());
    }



    //map the employee field to 
    @SchemaMapping(typeName = "SalaryType", field = "employees")
    public List<EmployeeOutput> getEmployeesWithSameSalary(SalaryType salaryType) {
        return allEmployees()
                    .stream()
                    .filter(e -> e.getSalaryType().equals(salaryType))
                    .map(employeeOutputMapper)
                    .collect(Collectors.toList());
    }
}


