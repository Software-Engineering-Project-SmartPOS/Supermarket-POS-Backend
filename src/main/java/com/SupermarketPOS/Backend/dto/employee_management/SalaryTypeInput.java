package com.SupermarketPOS.Backend.dto.employee_management;

public record SalaryTypeInput(
        Integer id,
        Float basicSalary,
        Float halfDaySalary,
        Float overTimeSalary,
        Float bonus

) {}
