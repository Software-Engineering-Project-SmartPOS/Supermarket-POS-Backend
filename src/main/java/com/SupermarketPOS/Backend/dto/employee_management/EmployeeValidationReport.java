package com.SupermarketPOS.Backend.dto.employee_management;

public record EmployeeValidationReport(
         Boolean isNameOkay,
         Boolean isNumberOkay,
         Boolean isEmailOkay
) {}
