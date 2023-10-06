package com.SupermarketPOS.Backend.dto.inventory_management;

import java.time.LocalDate;
import java.util.List;

public record PurchaseOrderInput(
        Integer id,
        Integer SupplierId,
        String expectedDate,
        String description,
        Float purchaseCost,
        List<PurchaseOrderItemInput> purchaseOrderItemList
) {
}
