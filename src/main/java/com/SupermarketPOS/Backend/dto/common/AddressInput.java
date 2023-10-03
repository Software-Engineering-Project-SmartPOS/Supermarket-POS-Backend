package com.SupermarketPOS.Backend.dto.common;

public record AddressInput(
        Integer id,
        String houseNumber,
        String street,
        String city,
        String district,
        String postalCode
) {
}
