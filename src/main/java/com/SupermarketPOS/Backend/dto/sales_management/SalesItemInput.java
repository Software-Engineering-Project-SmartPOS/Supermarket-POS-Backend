package com.SupermarketPOS.Backend.dto.sales_management;

public record SalesItemInput(
        Integer saleId, // used when updating
        Integer stockLevelId,
        Float quantity
) {}
