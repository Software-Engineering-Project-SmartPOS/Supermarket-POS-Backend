package com.SupermarketPOS.Backend.dto.sales_management;

import java.util.List;

public record RefundInput(
        Integer salesId,
        Float total,
        List<RefundItemInput> refundItemList
){
}