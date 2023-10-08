package com.SupermarketPOS.Backend.dto.inventory_management;

public record StockLevelInput(
         Integer stockArrivalId,
         String expiryDate,
         Float quantity,
         Float sellingPrice
){}
