package com.SupermarketPOS.Backend.model.employee_management;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double basicSalary;
    private Double halfDaySalary;
    private Double overTimeSalary;
    private Double bonus;
    private LocalDateTime createdAt;
    private LocalDateTime UpdatedAt;



    @OneToMany(mappedBy = "salaryType")
    private List<Employee> employees;

    public SalaryType(Double basicSalary, Double halfDaySalary, Double overTimeSalary, Double bonus) {
        this.basicSalary = basicSalary;
        this.halfDaySalary = halfDaySalary;
        this.overTimeSalary = overTimeSalary;
        this.bonus = bonus;
    }
}

