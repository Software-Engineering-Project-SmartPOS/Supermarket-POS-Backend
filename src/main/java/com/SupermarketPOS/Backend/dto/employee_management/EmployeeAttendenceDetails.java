package com.SupermarketPOS.Backend.dto.employee_management;

import javax.persistence.criteria.CriteriaBuilder;

public record EmployeeAttendenceDetails(
        Integer employeeId,
        String timeIn,
        String timeOut,
        String timeInDate,
        String timeOutDate
) {
}
