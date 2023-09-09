package com.SupermarketPOS.Backend.model.employee_management;

import com.SupermarketPOS.Backend.model.common.Month;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @OneToOne(optional = true)
    @JoinColumn(name = "advanePaymentRequestId")
    private AdvancePaymentResueqts advancePaymentResueqts;

    private Month salary_month;

    @ManyToOne
    @JoinColumn(name = "salaryTypeId")
    private SalaryType salaryType;




}
