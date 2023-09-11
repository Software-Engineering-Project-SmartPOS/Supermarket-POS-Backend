package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.BrandInput;
import com.SupermarketPOS.Backend.dto.inventory_management.BrandOutput;
import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import com.SupermarketPOS.Backend.service.inventory_management.BrandService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @MutationMapping
    public BrandOutput CreateBrand(@Argument BrandInput brandInput){
        System.out.println("braan");
        return brandService.AddBrand(brandInput);

    }

}
