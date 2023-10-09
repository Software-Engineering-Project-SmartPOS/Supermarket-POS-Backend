package com.SupermarketPOS.Backend.service.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.repository.sales_management.SalesItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SalesItemService {
    private final SalesItemRepository salesItemRepository;

    public SalesItemService(SalesItemRepository salesItemRepository) {
        this.salesItemRepository = salesItemRepository;
    }


}
