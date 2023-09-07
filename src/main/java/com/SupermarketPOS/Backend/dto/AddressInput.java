package com.SupermarketPOS.Backend.dto;

//public record AddressInput(
//        String street_name,
//        String house_number,
//        String zipcode
//
//
//) {
//
//}

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class AddressInput{
    private String street_name;
    private String house_number;
    private String zipcode;


    //here the parameter names for this class must be exactly same as the graphql
    // schemas input attributes names
    // but there is not a particular order for those parameters as well
    public AddressInput(String house_number,String street_name,  String zipcode) {
        this.street_name = street_name;
        this.house_number = house_number;
        this.zipcode = zipcode;
    }
}