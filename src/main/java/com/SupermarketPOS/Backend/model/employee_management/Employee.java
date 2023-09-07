package com.SupermarketPOS.Backend.model.employee_management;

import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Title;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Title title;
    private String first_name;
    private String middle_name;
    private String last_name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String number;

    private JobPosition job_role;

    @ManyToOne
    @JoinColumn(name = "salary_type_id")
    private SalaryType salary_type;

    private Boolean active;




}
