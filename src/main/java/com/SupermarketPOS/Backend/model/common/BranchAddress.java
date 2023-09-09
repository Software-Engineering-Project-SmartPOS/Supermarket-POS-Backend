package com.SupermarketPOS.Backend.model.common;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class BranchAddress extends AddressMain{
    @OneToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


}

