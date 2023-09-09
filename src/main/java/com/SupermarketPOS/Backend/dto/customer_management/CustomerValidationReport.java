package com.SupermarketPOS.Backend.dto.customer_management;

public record CustomerValidationReport(
        Boolean is_telephone_okay,
        Boolean is_email_okay
) {
}
