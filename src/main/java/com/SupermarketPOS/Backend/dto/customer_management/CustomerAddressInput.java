package com.SupermarketPOS.Backend.dto.customer_management;

public record CustomerAddressInput(
         String address ,
         String city ,
         String district ,
         String postalCode
) {
}
