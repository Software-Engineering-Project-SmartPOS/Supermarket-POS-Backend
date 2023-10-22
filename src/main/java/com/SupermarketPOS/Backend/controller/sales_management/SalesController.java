package com.SupermarketPOS.Backend.controller.sales_management;

import com.SupermarketPOS.Backend.dto.sales_management.SalesInput;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrder;
import com.SupermarketPOS.Backend.model.sales_management.Sale;
import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.service.sales_management.SalesService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class SalesController {
    private final SalesService salesService;
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_CASHIER"})
    @MutationMapping
    public Sale CreateSales(@Argument SalesInput salesInput, Principal principal) {
        return salesService.CreateSales( salesInput,principal);
    }

    @Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public Sale GetSalesById(@Argument Integer id, Principal principal) {
        return salesService.GetSalesById( id,principal);
    }

    @Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public List<Sale> GetAllSales(Principal principal) {
        return salesService.GetAllSales(principal);
    }

    @Secured({"ROLE_OWNER"})
    @QueryMapping
    public List<Sale> GetAllSalesForOwner(Principal principal) {
        return salesService.GetAllSalesForOwner(principal);
    }




    @SchemaMapping(typeName = "Sales", field = "salesItemList")
    public List<SalesItem> mapSalesItemList(Sale sales){
        return salesService.GetSalesById(sales.getId()).getSalesItemList();
    }


}
