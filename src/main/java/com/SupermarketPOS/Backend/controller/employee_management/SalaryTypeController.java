package com.SupermarketPOS.Backend.controller.employee_management;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.service.employee_management.SalaryTypeService;

import java.util.List;
import java.security.Principal;

@Controller
public class SalaryTypeController {
    private final SalaryTypeService salaryTypeService;
    public SalaryTypeController(SalaryTypeService salaryTypeService){
        this.salaryTypeService = salaryTypeService;
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public SalaryType AddSalaryType(@Argument SalaryTypeInput inputSalaryTypeDetails){
        return salaryTypeService.AddNewSalaryType(inputSalaryTypeDetails);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER"})
    @MutationMapping
    public SalaryType UpdateSalryType(@Argument SalaryTypeInput salaryTypeDetails){
        return salaryTypeService.UpdateTheSalaryType(salaryTypeDetails);
    }
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER"})
    @MutationMapping
    public String DeleteSalaryType(@Argument Integer id){
        return salaryTypeService.DeleteSalaryType(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_CASHIER","ROLE_CUSTOMER","ROLE_STORE_MANAGER","ROLE_SALES_ASSISTANT"})
    @QueryMapping
    public SalaryType salaryTypeById(@Argument Integer id , Principal principal){
        return salaryTypeService.FindById(id);
    }


    @QueryMapping
    public List<SalaryType> allSalaryTypes(){
        return salaryTypeService.getAllSalaryTypes();
    }


}
