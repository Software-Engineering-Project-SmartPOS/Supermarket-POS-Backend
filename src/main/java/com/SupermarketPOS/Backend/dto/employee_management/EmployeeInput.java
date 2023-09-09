package com.SupermarketPOS.Backend.dto.employee_management;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;

public record EmployeeInput(
        Title title,
        String firstName,
        String middleName,
        String lastName,
        String email,
        AddressInput address,
        String number,
        JobRole jobRole,
        SalaryTypeInput salaryType
) {}
