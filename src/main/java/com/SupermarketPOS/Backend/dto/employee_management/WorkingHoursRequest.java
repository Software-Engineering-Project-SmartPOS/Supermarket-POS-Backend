package com.SupermarketPOS.Backend.dto.employee_management;

public record WorkingHoursRequest(
        Integer employeeId,
        String from ,
        String to
) {
}
