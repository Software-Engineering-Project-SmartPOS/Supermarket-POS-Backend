package com.SupermarketPOS.Backend.model.employee_management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float basicSalary;

    private Float halfDaySalary;

    private Float overTimeSalary;

    private Float bonus;

    private LocalDateTime createdAt;

    private LocalDateTime UpdatedAt;

//    @OneToMany(mappedBy = "salaryType")
//    private List<Employee> employees;

    public SalaryType(Float basicSalary, Float halfDaySalary, Float overTimeSalary, Float bonus) {
        this.basicSalary = basicSalary;
        this.halfDaySalary = halfDaySalary;
        this.overTimeSalary = overTimeSalary;
        this.bonus = bonus;
    }
}

