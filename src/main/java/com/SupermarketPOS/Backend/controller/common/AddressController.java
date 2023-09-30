package com.SupermarketPOS.Backend.controller.common;


import com.SupermarketPOS.Backend.dto.common.AddressOutput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.service.common_services.AddressService;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@RestController
@Controller
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService =addressService;
    }


//    mapping the graphql query : allAddresses to get all the addresses in the database
    @QueryMapping
    public List<Address> allAddress(){
        return addressService.allTheAddresses();

    }

    @QueryMapping
//    @Secured("ROLE_ADMIN")

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CASHIER","ROLE_MANAGER"})
    public Address addressById(Principal principal,
            @Argument Integer id ){;
        // output from the server is nul;
        Address output =  addressService.getByTheId(id);
        if (output == null){
        }
        return output;
    }
}
