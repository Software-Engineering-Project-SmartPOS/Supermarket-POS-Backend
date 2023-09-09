package com.SupermarketPOS.Backend.model.customer_management;

public record CustomerOutput(
        Integer id,
        String name,
        String telephone,
        String email,
        CustomerAddress customerAddress,
        CustomerType customerType,
        Integer loyaltyId,
        String createdDate
) {
}
