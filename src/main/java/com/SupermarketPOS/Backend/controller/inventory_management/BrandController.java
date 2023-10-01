package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.BrandInput;
import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import com.SupermarketPOS.Backend.service.inventory_management.BrandService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }


    //creating the brand
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Brand CreateBrand(@Argument BrandInput brandInput, Principal principal){
        return brandService.AddBrand(brandInput,principal);
    }

    // Updating a brand
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER"})
    @MutationMapping
    public Brand UpdateBrand(@Argument BrandInput brandInput, Principal principal){
        return brandService.Update(brandInput,principal);
    }


    @QueryMapping
    public Brand GetBrandById(@Argument Integer id){
        return brandService.FindBrandById(id);
    }

    // Getting a brand by name
    @QueryMapping
    public Brand GetBrandByName(@Argument String name){
        return brandService.FindByName(name);
    }

    // Getting all the brands
    @QueryMapping
    public List<Brand> GetAllBrands(@Argument String name){
        return brandService.AllBrands();
    }




}
