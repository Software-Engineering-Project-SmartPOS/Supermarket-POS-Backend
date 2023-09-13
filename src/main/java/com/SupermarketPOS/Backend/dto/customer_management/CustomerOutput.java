package com.SupermarketPOS.Backend.dto.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.CustomerAddress;
import com.SupermarketPOS.Backend.model.customer_management.CustomerType;

import java.sql.Timestamp;

public record CustomerOutput(
        Integer id,
        String name,
        String telephone,
        String email,
        CustomerAddress customerAddress,
        CustomerType customerType,
        Integer loyaltyId,
        Timestamp createdDate,
        Timestamp firstVisited,
        Timestamp lastVisited
) {
}
