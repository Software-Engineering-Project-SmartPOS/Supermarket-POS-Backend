package com.SupermarketPOS.Backend.controller;


import com.SupermarketPOS.Backend.dto.EmployeeInput;
import com.SupermarketPOS.Backend.dto.EmployeeValidationReport;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService =employeeService;
    }

    //get all employees
    @QueryMapping
    public List<Employee> allEmployees(){
        return employeeService.getAllEmployees();
    }


    //add a new employee
    @MutationMapping
    public Employee AddEmployee(@Argument EmployeeInput employee_input){
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return employeeService.AddNewEmployee(employee_input);
    }

//    @MutationMapping
//    // this validate the given input
//    public EmployeeValidationReport isValidateEmployee(@Argument EmployeeInput employeeInput ){
//        return employeeService.Validate(employeeInput);
//    };

}
