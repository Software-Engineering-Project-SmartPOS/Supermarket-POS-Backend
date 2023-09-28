package com.SupermarketPOS.Backend.model.common;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String telephone;

    public Branch(String name, String telephone, Address branchAddress) {
        this.name = name;
        this.telephone = telephone;
        this.branchAddress = branchAddress;
    }

    @OneToOne
    @JoinColumn(name = "branchAddressId")
    private Address branchAddress;


    @OneToMany(mappedBy = "branch")
    private List<Employee> employeeList;

}
