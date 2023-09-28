package com.SupermarketPOS.Backend.dto;

import com.SupermarketPOS.Backend.model.common.Title;

public record OwnerInput(
        Integer id,
        Title title,
        String firstName,
        String middleName,
        String lastName,
        String number,
        String password,
        String email
) {
}

