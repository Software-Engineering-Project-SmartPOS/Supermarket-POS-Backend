package com.SupermarketPOS.Backend.dto.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.UnitOfMeasure;

public record ItemInput(
        Integer id, // used when updating
        String itemCode,
        String name,
        String barcodeNo,
        String description,
        Integer categoryId,
        Integer brandId,
        UnitOfMeasure unitOfMeasure,
        Boolean active,
        Integer reorderLevel,
        Boolean returnable
) {
}
