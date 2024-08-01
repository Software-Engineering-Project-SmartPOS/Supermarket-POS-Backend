package com.SupermarketPOS.Backend.dto.employee_management;

public record EmployeeAttendenceDetails(
        Integer employeeId,
        String timeIn,
        String timeOut,
        String timeInDate,
        String timeOutDate
) {
}
