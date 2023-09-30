package com.SupermarketPOS.Backend.dto.common;


public record AddressOutput(
        Integer id,
        String HouseNumber,
        String street,
        String city,
        String district,
        String postalCode
) {
}
//id: ID
//  houseNumber: String
//  street: String
//  city: String
//  district: String
//  postalCode: String