package com.SupermarketPOS.Backend.dto.inventory_management;

public record ItemSupplyInput(
        Integer id, //used when updating
        Integer supplierId,
        Integer itemId,
        Integer branchId,
        Float unitCost,
        Boolean active
) {
}
