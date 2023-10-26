package com.SupermarketPOS.Backend.controller.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.LoyaltyProgramInput;
import com.SupermarketPOS.Backend.model.customer_management.LoyaltyProgram;
import com.SupermarketPOS.Backend.service.customer_management.LoyaltyProgramService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class LoyaltyProgramController {
    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService){
        this.loyaltyProgramService = loyaltyProgramService;
    }


    @MutationMapping
    public LoyaltyProgram createLoyaltyProgram(@Argument LoyaltyProgramInput newProgramInput){
        return loyaltyProgramService.AddNewLoyaltyProgram(newProgramInput);
    }

    @QueryMapping
    public List<LoyaltyProgram> getAllLoyaltyPrograms(){
        return loyaltyProgramService.getAllLoyaltyPrograms();
    }

    @QueryMapping
    public Optional<LoyaltyProgram> getLoyaltyProgramById(@Argument Integer loyaltyProgramId){
        return loyaltyProgramService.findLoyaltyProgramById(loyaltyProgramId);
    }

    @MutationMapping
    public LoyaltyProgram updateLoyaltyProgram(@Argument LoyaltyProgramInput updateDetail){
        return loyaltyProgramService.UpdateLoyaltyProgram(updateDetail);
    }
    @MutationMapping
    public String InActivateLoyaltyProgram(@Argument Integer loyaltyProgramId){
        return loyaltyProgramService.InActivate(loyaltyProgramId);
    }

}
