package com.SupermarketPOS.Backend.dto.employee_management;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;

public record EmployeeOutput (
    Integer id,
    Title title,
    String firstName,
    String middleName,
    String lastName,
    String email,
    String number,
    JobRole jobRole,
    SalaryType salaryType,
    Boolean active

) {}
