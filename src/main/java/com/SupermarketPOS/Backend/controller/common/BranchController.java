package com.SupermarketPOS.Backend.controller.common;

import com.SupermarketPOS.Backend.dto.common.BranchInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.service.common_services.BranchService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
public class BranchController {
    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Branch addBranch(@Argument BranchInput branchInputDetails){
        return branchService.addNewBranch(branchInputDetails);
    }


    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    //mapping the address field of the Branch
    @SchemaMapping(typeName = "Branch", field = "address")
    public Address getAddressByBranch(Branch branch){
        return branchService.findBranchById(branch.getId()).getBranchAddress();
    }


}
