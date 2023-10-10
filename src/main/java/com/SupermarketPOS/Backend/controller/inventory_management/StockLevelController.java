package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelDetailInput;
import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelInput;
import com.SupermarketPOS.Backend.dto.inventory_management.StockTransferInput;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.service.inventory_management.StockLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class StockLevelController {
    private final StockLevelService stockLevelService;


    public StockLevelController(StockLevelService stockLevelService) {
        this.stockLevelService = stockLevelService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public StockLevel AddToStockLevel(@Argument StockLevelInput stockLevelInput, Principal principal) {
        return stockLevelService.addToStockLevel(stockLevelInput,principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public Boolean TransferToStall(@Argument StockTransferInput transferDetails, Principal principal) {
        if (stockLevelService.transferToStall(transferDetails,principal) == null){
            return false;
        }
        return  true;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public StockLevel GetStockLevelById(@Argument Integer id, Principal principal){
        return stockLevelService.getStockLevelById(id, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    @QueryMapping
    public List<StockLevel> AllStockLevels(Principal principal) {
        return stockLevelService.getAllStockLevels(principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    @QueryMapping
    public List<StockLevel> AllStockLevelsByStatus(@Argument StockLevelDetailInput itemDetail, Principal principal) {
        return stockLevelService.getAllStockLevelsByStatus(itemDetail,principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    @QueryMapping
    public List<StockLevel> AllStockLevelsByItemId(@Argument StockLevelDetailInput itemDetail, Principal principal) {
        return stockLevelService.getAllStockLevelsByItemId(itemDetail, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    @QueryMapping
    public List<StockLevel> AllStockLevelsByCategory(@Argument StockLevelDetailInput categoryDetail, Principal principal) {
        return stockLevelService.getAllStockLevelsByCategory(categoryDetail, principal);
    }
}
