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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String street_name;
    private String house_number;
    private String zipcode;

    @OneToMany(mappedBy = "address")
    private List<Employee> residing_employees;


    //constructor without residing_employees
    public Address(Integer id, String street_name, String house_number, String zipcode) {
        this.id = id;
        this.street_name = street_name;
        this.house_number = house_number;
        this.zipcode = zipcode;
    }

    //constructor without residing_employees and the id
    public Address(String street_name, String house_number, String zipcode) {
        this.street_name = street_name;
        this.house_number = house_number;
        this.zipcode = zipcode;
    }
}
