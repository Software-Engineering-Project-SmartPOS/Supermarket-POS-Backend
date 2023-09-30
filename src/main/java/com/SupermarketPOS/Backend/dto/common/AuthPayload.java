package com.SupermarketPOS.Backend.dto.common;

import com.SupermarketPOS.Backend.model.employee_management.Employee;

public record AuthPayload(
        String token,
        Employee user
) {
}
