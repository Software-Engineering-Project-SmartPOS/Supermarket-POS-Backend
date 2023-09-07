package com.SupermarketPOS.Backend.controller;


import com.SupermarketPOS.Backend.dto.AddressInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.repository.employee_management.AddressRepository;
import com.SupermarketPOS.Backend.service.AddressService;
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
                                        //         usedd to mannually add some addresses and test the @querymapping
                                        ////        Address address1 =  addressService.saveTheAddress( new Address("7891111111111111 Oak St", "Suite 303", "54321"));
                                        ////        Address address2 = addressService.saveTheAddress( new Address(5,"456 Elm St", "Unit 202", "67890"));
                                        ////        Address address3 = addressService.saveTheAddress( new Address(3,"123 Main St", "Apt 101", "12345"));
                                        ////        return  List.of(
                                        ////                address1,address2,address3
                                        ////        );
    }
    @QueryMapping
    public Address addressById(@Argument Integer id){;
        // output from the server is nul;
        Address output =  addressService.getByTheId(id);
        if (output == null){
            // need to work on if not address is found by the getById() function
//            System.out.println("nulllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
        }
        return output;
    }

    @MutationMapping
    public Address addAddress(@Argument AddressInput address_input){
        if (address_input == null) {
            throw new IllegalArgumentException("Address input is null");
        };
        return addressService.AddTheAddress(address_input);

    }
                                                    // used for testt
                                                    ////    @MutationMapping
                                                    ////    public Address addAnAddress(@Argument String rd){
                                                    ////        Address newAddress = new Address();
                                                    ////        newAddress.setHouse_number("51/5");
                                                    ////        newAddress.setStreet_name(rd);
                                                    ////        newAddress.setZipcode("212121212212121");
                                                    ////        return addressService.saveTheAddress(newAddress);
                                                    ////    }


}
