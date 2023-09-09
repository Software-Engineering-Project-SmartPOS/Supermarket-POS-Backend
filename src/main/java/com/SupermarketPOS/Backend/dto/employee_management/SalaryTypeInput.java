package com.SupermarketPOS.Backend.dto.employee_management;

public record SalaryTypeInput(
        Double basic_salary,
        Double half_day_salary,
        Double overtime_salary,
        Double  bonus
) {}
