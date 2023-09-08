package com.SupermarketPOS.Backend.dto;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;

public record EmployeeInput(
        Title title,
        String first_name,
        String middle_name,
        String last_name,
        String email,
        AddressInput address,
        String number,
        JobRole job_role,
        SalaryTypeInput salary_type
) {}
