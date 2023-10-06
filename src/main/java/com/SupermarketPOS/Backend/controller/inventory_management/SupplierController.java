package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.SupplierInput;
import com.SupermarketPOS.Backend.model.inventory_management.Supplier;
import com.SupermarketPOS.Backend.service.inventory_management.SupplierService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public Supplier GetSupplierById(@Argument Integer id) {
        // logic to fetch a supplier by ID using supplierService
        return supplierService.GetSupplierById(id);
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public Supplier GetSupplierByEmail(@Argument String name) {
        // Implement the logic to fetch a supplier by name using supplierService
        return supplierService.GetSupplierByEmail(name);
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
    @QueryMapping
    public List<Supplier> GetAllSuppliers() {
        // logic to fetch all suppliers using supplierService
        return supplierService.GetAllSuppliers();
    }


//
//    //following function are to be used by the particular employees in the branch / can get details only related to his branch only
//    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
//    @QueryMapping
//    public Supplier GetBranchSupplierById(@Argument Integer id,Principal principal) {
//        // logic to fetch a supplier by ID using supplierService
//        return supplierService.GetSupplierById(id,principal);
//    }
//
//    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
//    @QueryMapping
//    public Supplier GetBranchSupplierByEmail(@Argument String name,Principal principal) {
//        // Implement the logic to fetch a supplier by name using supplierService
//        return supplierService.GetSupplierByEmail(name,principal);
//    }
//
//    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER","ROLE_CASHIER"})
//    @QueryMapping
//    public List<Supplier> GetBranchAllSuppliers(Principal principal) {
//        // logic to fetch all suppliers using supplierService
//        return supplierService.GetAllSuppliers(principal);
//    }





    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER"})
    @MutationMapping
    public Supplier AddSupplier(@Argument SupplierInput supplierInput, Principal principal) {
        // logic to add a new supplier using supplierService
        return supplierService.CreateSupplier(supplierInput, principal);
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_OWNER","ROLE_STORE_MANAGER"})
    @MutationMapping
    public Supplier UpdateSupplier(@Argument SupplierInput supplierDetail, Principal principal) {
        // logic to update a supplier using supplierService
        return supplierService.UpdateSupplier(supplierDetail,principal);
    }
}
