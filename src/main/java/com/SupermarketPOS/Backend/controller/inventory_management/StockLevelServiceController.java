//package com.SupermarketPOS.Backend.controller.inventory_management;
//
//import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelDetailInput;
//import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelInput;
//import com.SupermarketPOS.Backend.dto.inventory_management.StockTransferInput;
//import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
//import com.SupermarketPOS.Backend.service.inventory_management.StockLevelService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class StockLevelController {
//    private final StockLevelService stockLevelService;
//
//
//    public StockLevelController(StockLevelService stockLevelService) {
//        this.stockLevelService = stockLevelService;
//    }
//
//    @MutationMapping
//    public StockLevel AddToStockLevel(StockLevelInput stockLevelInput, Principal principal) {
//        return stockLevelService.addToStockLevel(stockLevelInput,principal);
//    }
//
//    @MutationMapping
//    public Boolean TransferToStall(StockTransferInput transferDetails) {
//        return stockLevelService.transferToStall(transferDetails);
//    }
//
//    @QueryMapping
//    public List<StockLevel> AllStockLevels() {
//        return stockLevelService.getAllStockLevels();
//    }
//
//    @QueryMapping
//    public List<StockLevel> AllStockLevelsByStatus(StockLevelDetailInput itemDetail) {
//        return stockLevelService.getAllStockLevelsByStatus(itemDetail);
//    }
//
//    @QueryMapping
//    public List<StockLevel> AllStockLevelsByItemId(StockLevelDetailInput itemDetail) {
//        return stockLevelService.getAllStockLevelsByItemId(itemDetail);
//    }
//
//    @QueryMapping
//    public List<StockLevel> AllStockLevelsByCategory(StockLevelDetailInput categoryDetail) {
//        return stockLevelService.getAllStockLevelsByCategory(categoryDetail);
//    }
//}
