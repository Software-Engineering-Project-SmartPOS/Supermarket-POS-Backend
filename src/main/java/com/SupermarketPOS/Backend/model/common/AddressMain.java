package com.SupermarketPOS.Backend.model.common;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressMain {

    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipcode;

    public AddressMain(String streetName, String houseNumber, String zipcode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
    }
}
