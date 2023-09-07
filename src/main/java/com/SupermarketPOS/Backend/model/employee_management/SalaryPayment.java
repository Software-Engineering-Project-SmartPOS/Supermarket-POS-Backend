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
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(optional = true)
    @JoinColumn(name = "advane_payment_request_id")
    private AdvancePaymentResueqts advance_payment_resueqts;

    private Month salary_month;

    @ManyToOne
    @JoinColumn(name = "salary_type_id")
    private SalaryType salary_type;




}
