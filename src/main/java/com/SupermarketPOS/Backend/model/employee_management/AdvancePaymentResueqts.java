package com.SupermarketPOS.Backend.model.employee_management;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdvancePaymentResueqts {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String reason;
    private double Amount;
    private AdvancePaymentRequestStatus status;

    private LocalDateTime created_at;
    private LocalDate due_date;
    private Month month;

}
