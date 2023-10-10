package com.SupermarketPOS.Backend.controller.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.RefundItem;
import com.SupermarketPOS.Backend.service.sales_management.RefundItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class RefundItemController {
    private final RefundItemService refundItemService;

    public RefundItemController(RefundItemService refundItemService) {
        this.refundItemService = refundItemService;
    }

    @Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public RefundItem GetRefundItemById(@Argument Integer id, Principal principal) {
        return refundItemService.GetRefundItemById(id,principal);
    }


}
