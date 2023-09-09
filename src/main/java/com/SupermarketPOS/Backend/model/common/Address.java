package com.SupermarketPOS.Backend.model.common;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipcode;


    @OneToMany(mappedBy = "address")
    private List<Employee> residing_employees;


    //constructor without residing_employees
    public Address(Integer id, String streetName, String houseNumber, String zipcode) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
    }

    //constructor without residing_employees and the id
    public Address(String streetName, String houseNumber, String zipcode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
    }
}
