package com.SupermarketPOS.Backend.model.sales_management;

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
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sales;

    private float total;

    public Refund(Sale sales) {
        this.sales = sales;
    }
}
