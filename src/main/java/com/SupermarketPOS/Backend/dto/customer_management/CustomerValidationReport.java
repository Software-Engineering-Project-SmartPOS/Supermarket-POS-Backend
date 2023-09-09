package com.SupermarketPOS.Backend.dto.customer_management;

public record CustomerValidationReport(
        Boolean isTelephoneOkay,
        Boolean isEmailOkay
) {
}
