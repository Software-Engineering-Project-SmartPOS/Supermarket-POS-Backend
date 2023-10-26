package com.SupermarketPOS.Backend.model.employee_management;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public Attendance(LocalDateTime timeIn, LocalDateTime timeOut, LocalDate date, Employee employee) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.date = date;
        this.employee = employee;
    }

    // Constructors, getters, setters, etc.
}
