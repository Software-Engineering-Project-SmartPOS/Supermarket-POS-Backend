package com.SupermarketPOS.Backend.model.inventory_management;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Timestamp createAt;
    private Timestamp updateAt;
    @ManyToOne
    @JoinColumn(name = "updatedEmployeeId")
    private Employee updatedEmployee;
    @OneToMany(mappedBy = "category")
    private List<Item> itemList;

    public Category(String name, String description, Timestamp createAt, Employee updatedEmployee) {
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.updatedEmployee = updatedEmployee;
    }
}

