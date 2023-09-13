package com.SupermarketPOS.Backend.dto.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.CustomerType;

public record CustomerInput (
        Integer id,
        String name,
        String telephone,
        String email,
        CustomerAddressInput customerAddress,
        CustomerType customerType,
        String password
){
}
