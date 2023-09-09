package com.SupermarketPOS.Backend.model.employee_management;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long id;

    private LocalDateTime enterTime;
    private LocalDateTime leaveTime;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    // Constructors, getters, setters, etc.
}
