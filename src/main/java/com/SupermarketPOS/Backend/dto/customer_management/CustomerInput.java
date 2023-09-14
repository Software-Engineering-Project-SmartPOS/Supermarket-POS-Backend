package com.SupermarketPOS.Backend.dto.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.CustomerType;

public record CustomerInput (
        Integer id,
        String name,
        String telephone,
        String email,
        Integer addressId,
        String houseNumber,
        String street,
        String city,
        String district,
        String postalCode,
        CustomerType customerType,
        String password
){
}
