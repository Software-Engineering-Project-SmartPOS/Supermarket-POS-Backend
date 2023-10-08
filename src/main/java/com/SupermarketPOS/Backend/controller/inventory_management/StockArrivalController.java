package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderItemArrivalInput;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import com.SupermarketPOS.Backend.service.inventory_management.StockArrivalService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class StockArrivalController {
    private final StockArrivalService stockArrivalService;


    public StockArrivalController(StockArrivalService stockArrivalService) {
        this.stockArrivalService = stockArrivalService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<StockArrival> GetAllStockArrivals(@Argument boolean transferred, Principal principal) {
        return stockArrivalService.GetAllStockArrivals(transferred,principal);
    }

    @SchemaMapping(typeName = "StockArrival", field = "purchaseOrderItem")
    public PurchaseOrderItem MapPurchaseOrderItemOfTheStockArrival(StockArrival stockArrival){
        return stockArrivalService.GetPurchaseOrderItemByStockArrivalId(stockArrival.getId());
    }
}
