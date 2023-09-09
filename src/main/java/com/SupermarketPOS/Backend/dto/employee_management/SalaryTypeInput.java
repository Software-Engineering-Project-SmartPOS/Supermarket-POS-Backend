package com.SupermarketPOS.Backend.dto.employee_management;

public record SalaryTypeInput(
        Double basicSalary,
        Double halfDaySalary,
        Double overTimeSalary,
        Double  bonus
) {}
