package com.SupermarketPOS.Backend.dto.inventory_management;

public record StockTransferInput(
        Integer stockLevelId,
        Float transferQuantity
) {
}
