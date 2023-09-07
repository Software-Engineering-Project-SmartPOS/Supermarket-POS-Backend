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
    private Double basic_salary;
    private Double half_day_salary;
    private Double overtime_salary;
    private Double bonus;
    private LocalDateTime created_at;
    private LocalDateTime Updated_at;



    @OneToMany(mappedBy = "salary_type")
    private List<Employee> employees;


}

