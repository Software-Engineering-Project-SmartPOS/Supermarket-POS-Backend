package com.SupermarketPOS.Backend.dto;

public record SalaryTypeInput(
        Double basic_salary,
        Double half_day_salary,
        Double overtime_salary,
        Double  bonus
) {}
