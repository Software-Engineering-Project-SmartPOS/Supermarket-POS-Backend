package com.SupermarketPOS.Backend.controller.employee_management;


import com.SupermarketPOS.Backend.dto.common.FailedPayLoad;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController

public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeOutputMapper employeeOutputMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService ,
                              EmployeeOutputMapper employeeOutputMapper ){
        this.employeeService = employeeService;
        this.employeeOutputMapper = employeeOutputMapper;
    }

    //get all employees
    @QueryMapping
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    public List<Employee> allEmployees(){
        return employeeService.getAllEmployees();
    }


    //add a new employee
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Object AddEmployee(@Argument EmployeeInput employeeInput) {
        try {
            return employeeService.AddNewEmployee(employeeInput);
        }
        catch (Exception e){
            return new FailedPayLoad(e.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Object UpdateEmployee(@Argument EmployeeInput employeeInput) {
        return  employeeService.UpdateTheEmployee(employeeInput);
    }

    //delete employee using id
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Employee DeleteEmployee(@Argument Integer id){
        return employeeService.DeleteEmployee(id);
    }


    @QueryMapping
    // this validate the given input and send a validation report back
    public EmployeeValidationReport isValidateEmployee(@Argument EmployeeInput employeeInput ){
        return employeeService.Validate(employeeInput);
    };



    @SchemaMapping(typeName = "Employee" , field = "branch")
    public Branch getEmployeeBranch(Employee employee){
        return employeeService.getBranchByEmployeeId(employee.getId());
    }



    //map the employee field to
    @SchemaMapping(typeName = "SalaryType", field = "employees")
    public List<Employee> getEmployeesWithSameSalary(SalaryType salaryType) {
        return allEmployees();

    }
}


