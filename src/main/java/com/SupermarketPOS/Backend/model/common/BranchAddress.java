package com.SupermarketPOS.Backend.model.common;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class BranchAddress extends Address{
    @OneToOne
    @JoinColumn(name = "branchId")
    private Branch branch;
}

