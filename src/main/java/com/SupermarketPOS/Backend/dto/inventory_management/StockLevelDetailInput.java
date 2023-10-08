package com.SupermarketPOS.Backend.dto.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.StockLevelStatus;

public record  StockLevelDetailInput (
        Integer id,
        StockLevelStatus status
){
}
