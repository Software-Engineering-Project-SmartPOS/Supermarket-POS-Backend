package com.SupermarketPOS.Backend.controller.common;


import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.AddressRepository;
import com.SupermarketPOS.Backend.service.common_services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressController {
    private final AddressService addressService;


    @Autowired
    public AddressController(AddressService addressService){
        this.addressService =addressService;
    }


    @QueryMapping
//    mapping the graphql query : allAddresses to get all the addresses in the database
    public List<Address> allAddress(){
        return addressService.allTheAddresses();
                                        
    }
    @QueryMapping
    public Address addressById(@Argument Integer id){;
        // output from the server is nul;
        Address output =  addressService.getByTheId(id);
        if (output == null){
        }
        return output;
    }
}
