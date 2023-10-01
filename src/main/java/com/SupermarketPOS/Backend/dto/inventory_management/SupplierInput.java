package com.SupermarketPOS.Backend.dto.inventory_management;

import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.sql.Timestamp;

public record SupplierInput(
        Integer id, // used when updating supplier details

        String name,
        String landPhone,
        String mobilePhone,
        String email,

        Integer addressId, // used when updating supplier details
        String houseNumber,
        String street,
        String city,
        String district,
        String postalCode,

        Boolean active,
        Integer branchId
) {
}
