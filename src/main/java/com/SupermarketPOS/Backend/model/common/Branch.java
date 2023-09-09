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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String telephone;

    @OneToOne
    private BranchAddress branchAddress;

//    @OneToMany(mappedBy = "working_branch")
//    private List<Employee> employee_list;

}
