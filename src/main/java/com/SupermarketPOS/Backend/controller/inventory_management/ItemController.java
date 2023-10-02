package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.ItemInput;
import com.SupermarketPOS.Backend.model.inventory_management.Item;
import com.SupermarketPOS.Backend.service.inventory_management.ItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    // Creating an item
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Item CreateItem(@Argument ItemInput itemInput, Principal principal){
        return itemService.createItem(itemInput, principal);
    }

    // Updating an item
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Item UpdateItem(@Argument ItemInput itemInput, Principal principal){
        return itemService.updateItem(itemInput, principal);
    }

    @QueryMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    public Item GetItemById(@Argument Integer id){
        return itemService.getItemById(id);
    }

    // Getting an item by barcode
    @QueryMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    public Item GetItemByBarcode(@Argument String barcode){
        return itemService.getItemByBarcode(barcode);
    }

    // Getting an item by item code
    @QueryMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    public Item GetItemByItemCode(@Argument String itemCode){
        return itemService.getItemByItemCode(itemCode);
    }

    // You can add more query mappings as needed
}
