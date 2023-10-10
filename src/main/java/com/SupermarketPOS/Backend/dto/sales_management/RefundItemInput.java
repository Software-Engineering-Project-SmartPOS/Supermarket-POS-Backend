package com.SupermarketPOS.Backend.dto.sales_management;


public record RefundItemInput(
    Integer salesItemId,
    Float quantity
)
{}