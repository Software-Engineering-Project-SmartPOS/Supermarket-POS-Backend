package com.SupermarketPOS.Backend.model.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String houseNumber;
    private String street;
    private String city;
    private String district;
    private String postalCode;

    public void UpdateAddress(String houseNumber,String street, String city, String district, String postalCode) {
                this.houseNumber = houseNumber;
                this.street = street;
                this.city = city;
                this.district = district;
                this.postalCode = postalCode;
    }


    public Address(String houseNumber, String street, String city, String district, String postalCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

    
    
}
