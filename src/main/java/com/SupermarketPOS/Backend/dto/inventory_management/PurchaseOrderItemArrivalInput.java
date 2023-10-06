package com.SupermarketPOS.Backend.dto.inventory_management;

public record PurchaseOrderItemArrivalInput(
        Integer purchaseOrderItemId,
        Float receivedQuantity
) {
}
