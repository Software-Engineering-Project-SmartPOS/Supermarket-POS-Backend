package com.SupermarketPOS.Backend.controller.employee_management;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.service.employee_management.SalaryTypeService;

import java.util.List;

@Controller
public class SalaryTypeController {
    private final SalaryTypeService salaryTypeService;
    public SalaryTypeController(SalaryTypeService salaryTypeService){
        this.salaryTypeService = salaryTypeService;
    }


    @MutationMapping
    public SalaryType AddSalaryType(@Argument SalaryTypeInput inputSalaryTypeDetails){
        return salaryTypeService.AddNewSalaryType(inputSalaryTypeDetails);
    }

    @QueryMapping
    public SalaryType salaryTypeById(@Argument Integer id ){
        return salaryTypeService.FindById(id);
    }

    @QueryMapping
    public List<SalaryType> allSalaryTypes(){
        return salaryTypeService.getAllSalaryTypes();
    }
}
