package com.SupermarketPOS.Backend.dto.employee_management;

//public record AddressInput(
//        String streetName,
//        String houseNumber,
//        String zipcode
//
//
//) {
//
//}

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class AddressInput{
    private String streetName;
    private String houseNumber;
    private String zipcode;



    //here the parameter names for this class must be exactly same as the graphql
    // schemas input attributes names
    // but there is not a particular order for those parameters as well
    public AddressInput(String houseNumber,String streetName,  String zipcode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
    }
}