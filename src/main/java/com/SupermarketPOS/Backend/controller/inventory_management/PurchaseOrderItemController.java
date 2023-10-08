package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderItemArrivalInput;
import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderItemInput;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.service.inventory_management.PurchaseOrderItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class PurchaseOrderItemController {
    private final PurchaseOrderItemService purchaseOrderItemService;

    public PurchaseOrderItemController(PurchaseOrderItemService purchaseOrderItemService) {
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public PurchaseOrderItem PurchaseOrderItemArrival(@Argument PurchaseOrderItemArrivalInput arrivalDetails, Principal principal) {
        return purchaseOrderItemService.HandleItemArrival(arrivalDetails, principal);
    }


//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
//    @QueryMapping
//    public List<PurchaseOrderItem> purchaseOrderItems() {
//        // Logic to fetch all purchase order items using purchaseOrderItemService
//        return purchaseOrderItemService.GetAllPurchaseOrderItems();
//    }
//
//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER", "ROLE_STORE_MANAGER"})
//    @QueryMapping
//    public PurchaseOrderItem purchaseOrderItem(@Argument Integer id) {
//        // Logic to fetch a purchase order item by ID using purchaseOrderItemService
//        return purchaseOrderItemService.GetPurchaseOrderItemById(id);
//    }
//
//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
//    @MutationMapping
//    public PurchaseOrderItem createPurchaseOrderItem(@Argument PurchaseOrderItemInput input) {
//        // Logic to create a new purchase order item using purchaseOrderItemService
//        return purchaseOrderItemService.CreatePurchaseOrderItem(input);
//    }
//
//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
//    @MutationMapping
//    public PurchaseOrderItem updatePurchaseOrderItem(@Argument Integer id, @Argument PurchaseOrderItemInput input) {
//        // Logic to update a purchase order item by ID using purchaseOrderItemService
//        return purchaseOrderItemService.UpdatePurchaseOrderItem(id, input);
//    }
//
//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
//    @MutationMapping
//    public boolean deletePurchaseOrderItem(@Argument Integer id) {
//        // Logic to delete a purchase order item by ID using purchaseOrderItemService
//        return purchaseOrderItemService.DeletePurchaseOrderItem(id);
//    }
}
