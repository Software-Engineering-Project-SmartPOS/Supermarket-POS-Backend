package com.SupermarketPOS.Backend.dto.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import graphql.GraphQLException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeOutputMapper implements Function<Employee, EmployeeOutput> {

    @Override
    public EmployeeOutput apply(Employee employee) {
        if (employee == null) {
            throw new GraphQLException("Employee is null");
        }
        return new EmployeeOutput(
                employee.getId(),
                employee.getTitle(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getBranch(),
                employee.getEmail(),
                employee.getNumber(),
                employee.getJobRole(),
                employee.getSalaryType(),
                employee.getActive()
        );
    }

}
