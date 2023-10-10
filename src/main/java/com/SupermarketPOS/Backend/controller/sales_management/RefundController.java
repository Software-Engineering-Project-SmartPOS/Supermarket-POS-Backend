package com.SupermarketPOS.Backend.controller.sales_management;

import com.SupermarketPOS.Backend.dto.sales_management.RefundInput;
import com.SupermarketPOS.Backend.dto.sales_management.SalesInput;
import com.SupermarketPOS.Backend.model.sales_management.Refund;
import com.SupermarketPOS.Backend.model.sales_management.RefundItem;
import com.SupermarketPOS.Backend.model.sales_management.Sale;
import com.SupermarketPOS.Backend.service.sales_management.RefundService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class RefundController {
    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @Secured({"ROLE_CASHIER"})
    @MutationMapping
    public Refund CreateRefund(@Argument RefundInput refundDetails, Principal principal) {
        return refundService.CreateNewRefund( refundDetails,principal);
    }

    @Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public List<Refund> GetAllRefunds(Principal principal) {
        return refundService.GetAllTheRefunds(principal);
    }



}
