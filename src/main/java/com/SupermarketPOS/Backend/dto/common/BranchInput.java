package com.SupermarketPOS.Backend.dto.common;

public record BranchInput (
        Integer id,
        String name,
        String telephone,
        Integer addressId,
        String houseNumber,
        String street,
        String city,
        String district,
        String postalCode
    ){}
