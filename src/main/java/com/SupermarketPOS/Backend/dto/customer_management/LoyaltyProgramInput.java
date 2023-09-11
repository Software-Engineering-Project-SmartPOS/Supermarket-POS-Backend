package com.SupermarketPOS.Backend.dto.customer_management;

public record LoyaltyProgramInput(
        Integer id,
        String loyaltyProgramName,
        String description,
        Integer pointsThreshold,
        Float discountPercentage
) {
}
