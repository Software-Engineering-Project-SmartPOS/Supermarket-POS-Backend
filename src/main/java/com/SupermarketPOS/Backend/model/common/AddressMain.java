package com.SupermarketPOS.Backend.model.common;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

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
    private String street_name;
    private String house_number;
    private String zipcode;

    public AddressMain(String street_name, String house_number, String zipcode) {
        this.street_name = street_name;
        this.house_number = house_number;
        this.zipcode = zipcode;
    }
}
