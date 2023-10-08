package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderInput;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrder;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderStatus;
import com.SupermarketPOS.Backend.service.inventory_management.PurchaseOrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    //all the purchases in the callers branch
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<PurchaseOrder> AllPurchaseOrders(Principal principal) {
        // Logic to fetch all purchase orders using purchaseOrderService
        return purchaseOrderService.GetAllPurchaseOrders(principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public PurchaseOrder PurchaseOrderById(@Argument Integer id, Principal principal) {
        // Logic to fetch a purchase order by ID using purchaseOrderService
        return purchaseOrderService.GetPurchaseOrderById(id, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<PurchaseOrder> AllPurchaseOrdersByStatus(@Argument PurchaseOrderStatus status, Principal principal) {
        // Logic to fetch a purchase order by ID using purchaseOrderService
        return purchaseOrderService.GetPurchaseOrdersByStatus(status, principal);
    }



    @Secured({"ROLE_ADMIN", "ROLE_MANAGER","ROLE_STORE_MANAGER"})
    @QueryMapping
    public List<PurchaseOrder> AllPurchaseOrdersBySupplierId(@Argument Integer supplierId, Principal principal) {
        // Logic to fetch a purchase order by ID using purchaseOrderService
        return purchaseOrderService.GetPurchaseOrderBySupplierId(supplierId, principal);
    }



    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public PurchaseOrder CreatePurchaseOrder(@Argument PurchaseOrderInput purchaseOrderInput , Principal principal) {
        // Logic to create a new purchase order using purchaseOrderService
        return purchaseOrderService.CreatePurchaseOrder(purchaseOrderInput,principal);
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
//    @MutationMapping
//    public PurchaseOrder UpdatePurchaseOrder(@Argument PurchaseOrderInput input) {
//        // Logic to update a purchase order by ID using purchaseOrderService
//        return purchaseOrderService.UpdatePurchaseOrder(input);
//    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    @MutationMapping
    public boolean DeletePurchaseOrder(@Argument Integer id, Principal principal) {
        // Logic to delete a purchase order by ID using purchaseOrderService
        return purchaseOrderService.DeletePurchaseOrder(id , principal);
    }



    @SchemaMapping(typeName = "PurchaseOrder", field = "purchaseOrderItemList")
    public List<PurchaseOrderItem> MappingOrderItemToOrder(PurchaseOrder purchaseOrder){
        //get purchaseOrderItemList
        return purchaseOrderService.GetPurchaseOrderItemByPurchaseOrderId(purchaseOrder.getId());
    }

}
