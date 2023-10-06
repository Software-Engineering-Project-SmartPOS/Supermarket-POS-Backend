package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.ItemSupplyInput;
import com.SupermarketPOS.Backend.model.inventory_management.ItemSupply;
import com.SupermarketPOS.Backend.service.inventory_management.ItemSupplyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class ItemSupplyController {
    private final ItemSupplyService itemSupplyService;

    public ItemSupplyController(ItemSupplyService itemSupplyService) {
        this.itemSupplyService = itemSupplyService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public ItemSupply GetActiveItemSupplyById(@Argument Integer id , Boolean Active) {
        // logic to fetch an item supply by ID using itemSupplyService
        return itemSupplyService.getActiveItemSupplyById(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<ItemSupply> GetActiveItemSuppliesBySupplierId(@Argument Integer supplierId) {
        // logic to fetch item supplies by supplier ID using itemSupplyService
        return itemSupplyService.getActiveItemSuppliesBySupplierId(supplierId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<ItemSupply> GetActiveItemSuppliesByItemId(@Argument Integer itemId) {
        // logic to fetch item supplies by item ID using itemSupplyService
        return itemSupplyService.getActiveItemSuppliesByItemId(itemId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<ItemSupply> GetActiveItemSuppliesByBranchId(@Argument Integer branchId) {
        // logic to fetch item supplies by branch ID using itemSupplyService
        return itemSupplyService.getActiveItemSuppliesByBranchId(branchId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public ItemSupply CreateItemSupply(@Argument ItemSupplyInput itemSupplyInput, Principal principal) {
        // logic to create a new item supply using itemSupplyService
        return itemSupplyService.CreateItemSupply(itemSupplyInput, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public ItemSupply UpdateItemSupply(@Argument ItemSupplyInput itemSupplyInput, Principal principal) {
        // logic to update an item supply using itemSupplyService
        return itemSupplyService.UpdateItemSupplyItemSupplyInput(itemSupplyInput, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER",  "ROLE_STORE_MANAGER"})
    @MutationMapping
    public boolean DeleteItemSupply(@Argument Integer id) {
        // logic to delete an item supply by ID using itemSupplyService
        return itemSupplyService.deleteItemSupply(id);
    }
}
