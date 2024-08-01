package com.SupermarketPOS.Backend.dto.inventory_management;

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
