package com.SupermarketPOS.Backend.controller.sales_management;

import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.model.sales_management.Sale;
import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.service.sales_management.SalesItemService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class SalesItemController {
    private final SalesItemService salesItemService;

    public SalesItemController(SalesItemService salesItemService) {
        this.salesItemService = salesItemService;
    }


    @Secured({"ROLE_MANAGER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public List<SalesItem> GetAllSalesItems(Principal principal) {
        return salesItemService.GetAllTheSalesItem(principal) ;
    }


    @Secured({"ROLE_OWNER"})
    @QueryMapping
    public List<SalesItem> GetAllSalesItems1() {
        return salesItemService.GetAllTheSalesItem() ;
    }


    @SchemaMapping(typeName = "SalesItem", field = "sales")
    public Sale mapSalesInSalesItem(SalesItem salesItem){
        return salesItemService.GetSalesItemById(salesItem.getId()).getSales();
    }


    @SchemaMapping(typeName = "SalesItem", field = "stockLevel")
    public StockLevel mapStockLevelInSalesItem(SalesItem salesItem){
        return salesItemService.GetSalesItemById(salesItem.getId()).getStockLevel();
    }
}
