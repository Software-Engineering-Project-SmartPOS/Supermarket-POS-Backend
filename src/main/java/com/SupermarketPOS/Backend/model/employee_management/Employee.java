package com.SupermarketPOS.Backend.model.employee_management;

import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.common.Title;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // Define the unique constraint
    //@Table(uniqueConstraints = {
    //        @UniqueConstraint(columnNames = "email"),
    //        @UniqueConstraint(columnNames = {"first_name", "middle_name", "last_name"})
    //})

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Title title;
    private String first_name;
    private String middle_name;
    private String last_name;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    private String number;

    private JobRole job_role;

    @ManyToOne
    @JoinColumn(name = "salary_type_id")
    private SalaryType salary_type;

    private Boolean active;

    public Employee(Title title, String first_name, String middle_name, String last_name,String email, Address address, String number, JobRole job_role, SalaryType salary_type, Boolean active) {
        this.title = title;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.email= email;
        this.address = address;
        this.number = number;
        this.job_role = job_role;
        this.salary_type = salary_type;
        this.active = active;
    }
}
