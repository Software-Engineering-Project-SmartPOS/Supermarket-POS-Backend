package com.SupermarketPOS.Backend.dto.employee_management;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;

public record EmployeeInput(
        Title title,
        String firstName,
        String middleName,
        String lastName,
        String email,
        Integer addressId,
        String houseNumber,
        String street,
        String city,
        String district,
        String postalCode,
        String phoneNumber,
        JobRole jobRole,
        SalaryTypeInput salaryType
) {}
