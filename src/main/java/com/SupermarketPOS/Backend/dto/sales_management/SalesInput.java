package com.SupermarketPOS.Backend.dto.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.PaymentType;

import java.util.List;

public record SalesInput (
        Integer id, // used when updating the data
        String barcodeNo, // used when updating / returning
        Integer customerId,
        List<SalesItemInput> salesItemsInput,
        Float total,
        PaymentType paymentType
) {}

