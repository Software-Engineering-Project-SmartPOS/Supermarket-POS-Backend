package com.SupermarketPOS.Backend.controller.employee_management;


import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeOutput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeOutputMapper;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeOutputMapper employeeOutputMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService , EmployeeOutputMapper employeeOutputMapper){
        this.employeeService = employeeService;
        this.employeeOutputMapper = employeeOutputMapper;
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


