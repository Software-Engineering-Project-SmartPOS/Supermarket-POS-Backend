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

import java.util.List;
@RestController
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

    @PostMapping("/addressRest")
    @Secured("ROLE_OWNER")
    public AddressOutput addressByIdREST(){;
        // output from the server is nul;
        System.out.println("in the addressByIdREST function");
        Address output =  addressService.getByTheId(1);
        if (output == null){
        }

//        return new ResponseEntity<>(output, HttpStatus.OK);
        return new AddressOutput(
                output.getId(),
                output.getHouseNumber(),
                output.getStreet(),
                output.getCity(),
                output.getDistrict(),
                output.getPostalCode()

        );
//        return output;
    }

    @QueryMapping
    @Secured("ROLE_ADMIN")
    public AddressOutput addressById(@Argument Integer id){;
        // output from the server is nul;
        Address output =  addressService.getByTheId(id);
        if (output == null){
        }
        return new AddressOutput(
                output.getId(),
                output.getHouseNumber(),
                output.getStreet(),
                output.getCity(),
                output.getDistrict(),
                output.getPostalCode()

        );
    }
}
