package com.SupermarketPOS.Backend.dto;

public record EmployeeValidationReport(
         Boolean is_name_okay,
         Boolean is_number_okay,
         Boolean is_email_okay
) {}
