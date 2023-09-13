package com.SupermarketPOS.Backend.controller.employee_management;


import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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


    @SchemaMapping(typeName = "Address", field = "residingEmployees")
    public List<Employee> allEmployeesByAddressId(@Argument Address address){
        return ;
    }

    //add a new employee
    @MutationMapping
    public Employee AddEmployee(@Argument EmployeeInput employeeInput){
        return employeeService.AddNewEmployee(employeeInput);
    }

    @MutationMapping
    // this validate the given input and send a validation report back
    public EmployeeValidationReport isValidateEmployee(@Argument EmployeeInput employeeInput ){
        return employeeService.Validate(employeeInput);
    };

}
