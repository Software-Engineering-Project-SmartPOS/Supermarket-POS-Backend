package com.SupermarketPOS.Backend.controller.common;

import com.SupermarketPOS.Backend.dto.common.BranchInput;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.service.common_services.BranchService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @MutationMapping
    public Branch addBranch(@Argument BranchInput branchInputDetails){
        return branchService.addNewBranch(branchInputDetails);
    }


}
