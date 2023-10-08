package com.SupermarketPOS.Backend.dto.inventory_management;

public record PurchaseOrderItemInput(
        Integer id, //used when updating
        Integer purchaseOrderId,
        Integer itemId,
        Integer itemSupplyId,
        Float quantity,
        Float purchaseItemUnitCost,
        Float totalCost,
        Float receivedQuantity
) {
}
