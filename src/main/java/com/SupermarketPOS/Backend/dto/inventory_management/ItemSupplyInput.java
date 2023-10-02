package com.SupermarketPOS.Backend.dto.inventory_management;

import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.inventory_management.Item;
import com.SupermarketPOS.Backend.model.inventory_management.Supplier;

import java.sql.Timestamp;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ItemSupplyInput(
        Integer id, //used when updating
        Integer supplierId,
        Integer itemId,
        Integer branchId,
        Float unitCost,
        Boolean active
) {
}
