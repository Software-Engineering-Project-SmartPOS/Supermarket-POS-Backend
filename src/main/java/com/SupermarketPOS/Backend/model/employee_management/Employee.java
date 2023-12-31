package com.SupermarketPOS.Backend.model.employee_management;

import com.SupermarketPOS.Backend.model.common.*;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // Define the unique constraint
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Title title;
    private String firstName;
    private String middleName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    private String number;
    private JobRole jobRole;

    @ManyToOne
    @JoinColumn(name = "salaryTypeId")
    private SalaryType salaryType;
    private Boolean active;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Employee(Title title, String firstName, String middleName, String lastName,  String email, Address address, String number, JobRole jobRole, SalaryType salaryType, Boolean active , String password ,Branch branch) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.number = number;
        this.jobRole = jobRole;
        this.salaryType = salaryType;
        this.active = active;
        this.password = password;
        this.branch = branch;
    }

    public Employee(Title title, String firstName, String middleName, String lastName,  String email, Address address, String number, JobRole jobRole, SalaryType salaryType, Boolean active , String password ,Branch branch, Timestamp createdAt) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.number = number;
        this.jobRole = jobRole;
        this.salaryType = salaryType;
        this.active = active;
        this.password = password;
        this.branch = branch;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
