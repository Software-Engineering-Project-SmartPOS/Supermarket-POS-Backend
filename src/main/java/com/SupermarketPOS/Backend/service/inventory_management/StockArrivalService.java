package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import com.SupermarketPOS.Backend.repository.inventory_management.StockArrivalRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StockArrivalService {
    private final StockArrivalRepository stockArrivalRepository;

    public StockArrivalService(StockArrivalRepository stockArrivalRepository) {
        this.stockArrivalRepository = stockArrivalRepository;
    }

    public StockArrival CreateNewStockArrival(PurchaseOrderItem purchaseOrderItem, Float quantity,  Timestamp arrivedAt){
        StockArrival newStockArrival = new StockArrival(
                purchaseOrderItem,
                quantity,
                null,
                arrivedAt
        );
        return stockArrivalRepository.save(newStockArrival);
    }
}
